package modal;
import java.util.*;

import controller.ObservadoIF;
import controller.ObservadorIF;
import view.ViewAPI;
import controller.Moment;

class Jogo implements ObservadoIF {   
	private Moment moment = Moment.getInstance();
	private static Jogo instance;
	List<ObservadorIF> observadores = new ArrayList<ObservadorIF>();

	private Player players[] = new Player[4];
	private Tabuleiro board;
	private Dado d;
	
	private int 	currentPeca;
	private Peca 	lastMovedPeca 	= null;
	private boolean captureFlag 	= false;
	private int 	currentPlayer 	= 0;
	private int 	currentDice 	= -1;
	private int 	qtd_6_rolados 	= 0;
	
	private Casa	clickedCasa 	;
// ____________________________________________________________________________________________________________________________
//

	protected Jogo() {
		this.addObservador(ViewAPI.getInstance());
	}
	
	//	Inicializacao --------------------------------------
	protected void initialize_jogo() {
		start_players();
		start_board();
		start_dado();
		start_game();
	}
	protected void start_players() {
		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i);
		}
	}
	protected void start_board() {
		board = Tabuleiro.getInstance();
	}
	protected void start_dado() {
		d = Dado.getInstance();
	}
	protected void start_game() {
		update_capture(false);
		set_currentPlayer(0);
		currentDice = 5;
		qtd_6_rolados = 0;
//		Inicializa as casas iniciais de cada jogador com referencias as casas inicias respectivas no tabuleiro
		for (int count = 0; count < 4; count++) {
			for (int i = 0; i < 4; i++) {
				get_player(count).get_peca(i).change_casa(board.get_casasIniciaisIndex(count));
				board.get_casasIniciaisIndex(count).add_peca(get_player(count).get_peca(i));
			}
		}
//		Move a primeira peca vermelha para a casa de saida
		Peca p = players[0].get_peca(0);
		p.move_to_casa_de_saida();
		set_lastMovedPeca(p);
		end_turn();
	}
	
	//	Operacoes -------------------------------------------
	protected void initializeTurn(int pathIndex, int finalPathIndex, int diceVal) {
		int listIndex;		
		int listType;	// 0 -> path, 1 -> casasIniciais, 2 -> finalPath (~ retaFinal)
		Casa c;
		Peca p;
		Player ply = players[currentPlayer];
//		if current player cant move, skip
		if (!(ply.can_move(diceVal))) {
			end_turn();
			return;
		}
		else if (diceVal == 5) {
			c = board.get_casasIniciaisIndex(ply.get_id());
			if (c.get_num_pecas() != 0 && board.get_casaDeSaida(ply.get_id()).is_casa_vaga(c.get_primeira_peca_player(ply))) {
				p = c.get_primeira_peca_player(ply);
				p.move_to_casa_de_saida();
				set_lastMovedPeca(p);
				end_turn();
				return;
			}
		}
		else if (diceVal == 6) {
			qtd_6_rolados++;
			if (qtd_6_rolados == 3) {
				c = lastMovedPeca.get_currentCasa();
				if(c.get_tipo() != 5 && c.get_tipo() != 4) {
					lastMovedPeca.move_to_base();
				}
				end_turn();
				return;
			}
			
			// verifica se player tem barreira
			else if (ply.get_barrier() != null){
				p = ply.get_barrier();
				p.move(diceVal);
				set_lastMovedPeca(p);
				return;
			}
		}
		
		if(finalPathIndex > -1){
			System.out.println("finalPathIndex = " + finalPathIndex);
			clickedCasa = board.get_retaFinalIndex(finalPathIndex, currentPlayer);
			listIndex = finalPathIndex;
			listType = 2;
		}
		else if(pathIndex > -1) {
			System.out.println("pathIndex = " + pathIndex);
			clickedCasa = board.get_pathIndex(pathIndex);
			listIndex = pathIndex;
			listType = 0;	
		}
		else {
			clickedCasa = board.get_casasIniciaisIndex(currentPlayer);
			listIndex = -1;
			listType = 1;	
		}
		
//		TODO:
//		set_clickedCasa(listIndex, listType); // verificar se na casa tem pecas, caso tiver, e
//		setCurrentPeca(listIndex, listType);
		
		turn();
	}
	
	protected void turn() {
		Player ply = players[currentPlayer];
		Peca p = null;
		Casa c;
		
//		System.out.println("player " + currentPlayer + " turn");
		System.out.println("dado = " + currentDice);
		print_map();
		
		for (int i = 0; i < 4; i++) {
			if (clickedCasa.get_peca(i) != null) {
				if (clickedCasa.get_peca(i).get_cor() == currentPlayer) {
					p = clickedCasa.get_peca(i);
					break;
				}
			}
		}
		if (p == null) return;
		
		if (p.can_move(currentDice)) {
			p.move(currentDice);
			set_currentPeca(ply, p);
			set_lastMovedPeca(p);			
		}
		else return;

		while (lastMovedPeca.get_currentCasa().is_casa_final() || captureFlag == true) {
			if (captureFlag == true) update_capture(false);
			if(ply.can_move(6)) {
				p = ply.pick_peca(6);
				p.move(6);
				set_lastMovedPeca(p);
			}
			else break;
		}
		
		if (check_EndGameCondition() != -1) {
			end_game();
		}
		if (currentDice == 6) {
			return;
		}
		end_turn();
		return;		
	}
	protected void end_turn() {
		currentPlayer = (currentPlayer + 1) % 4;
		qtd_6_rolados = 0;
		captureFlag = false;
		atualizaObservadores();
	}
	
	protected void new_game() {
		board.clear_tabuleiro();
		start_game();
	}

	protected int check_EndGameCondition() {
		if 		(board.get_casaFinal(0).get_num_pecas() == 4) return 0;
		else if	(board.get_casaFinal(1).get_num_pecas() == 4) return 1;
		else if	(board.get_casaFinal(2).get_num_pecas() == 4) return 2;
		else if	(board.get_casaFinal(3).get_num_pecas() == 4) return 3;
		return -1;
	}
	protected void end_game() {
		determinePodium();
		System.out.print("\n\n\n ======================     FIM DE JOGO    ======================\n\n\n");
	}
	protected int[][] determinePodium() {
		TreeSet<Integer> pecas_count = new TreeSet<>();
		List<Integer> pecas_count_lst = new ArrayList<>(pecas_count);
		int[][] podio = new int[4][2]; 		//Colocacao de cada player
		
//		TODO: corrigir implementacao deste metodo: a regra diz que a pontuacao depende da distancia dos peoes do jogador ate a final ou algo parecido
//		for(int i = 0; i < 4; i++)
//			pecas_count.add(t.get_casa_final(i).get_num_pecas());
//		
//		for(int i = 0; i < 4; i++)
//			podio[i] = pecas_count_lst.indexOf(t.get_casa_final(i).get_num_pecas());
//		
		return podio;
	}
		
	protected void captura(Casa c) {
		Peca p = c.get_peca(0);
		if (p == null) {
			p = c.get_peca(1);
		}
		p.move_to_base();
		update_capture(true);
		atualizaObservadores();
	}
	protected void update_capture(boolean b) {
		captureFlag = b;
	}
	
	//	SET ----------------------------------------
	protected void set_currentPlayer(int t) {
		currentPlayer = t;
	}
	protected void set_dice(int t) {
		currentDice = t;
	}
	
	protected void set_currentPeca(Player ply, Peca p) {
		int i;
		for (i = 0; i < 4; i++) {
			if (ply.get_peca(i).equals(p)) break;
		}
		currentPeca = (p.get_cor() * 4) + i; 
	}
	protected void set_lastMovedPeca(Peca p) {
		lastMovedPeca = p;
	}
	
	
	protected void set_set_clickedCasa(int listIndex, int listType) {
		// TODO: implementar
		
	}
	//	GET ----------------------------------------
	protected Peca		get_LastMovedPeca	() {
		return lastMovedPeca;
	}
	protected Player 	get_player			(int id){
		return players[id];
	}
	protected int 		get_currentPlayer	() {
		return currentPlayer;
	}
	protected int 		get_currentDice		() {
		return currentDice;
	}
	protected Casa		get_clickedCasa		() {
		return clickedCasa;
	}
	
	// Implementacao da interface ObservadoIF ----------------
	public void addObservador			(ObservadorIF o) {
		observadores.add(o);
	}
	public void removeObservador		(ObservadorIF o) {
		observadores.remove(o);
	}
	public void atualizaObservadores	() {
		ListIterator<ObservadorIF> li = observadores.listIterator();
		while(li.hasNext()) {
			li.next().notify(this);
		}
	}

	public void get() {
		int[][] i = new int[2][2];
		moment.set_casasIniciais(board.getObs_casasIniciais());
		moment.set_path(board.getObs_path());
		moment.set_retaFinalVermelho(board.getObs_rf_vermelho());
		moment.set_retaFinalVerde(board.getObs_rf_verde());
		moment.set_retaFinalAmarelo(board.getObs_rf_amarelo());
		moment.set_retaFinalAzul(board.getObs_rf_azul());
		moment.set_podio(i);
		moment.set_player(currentPlayer);
	}

    
	//	Singleton ------------------------------------------
   	public static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}

   	
   	
   	

//	TODO remover==============================================
	protected void print_map() {
		ArrayList<Casa> map = board.get_path();
		ArrayList<Casa> r1 = board.get_rf_vermelho();
		ArrayList<Casa> r2= board.get_rf_verde();
		ArrayList<Casa> r3 = board.get_rf_amarelo();
		ArrayList<Casa> r4 = board.get_rf_azul();
		ArrayList<Casa> ini = board.get_casas_iniciais();
		
		for (int i = 0; i < 4; i++) {
		System.out.print(ini.get(i).get_num_pecas() + "\t\t");
		}
		System.out.println();
		for (int i = 0; i < 52; i++) {
			System.out.print(map.get(i).get_num_pecas() + " ");
		}
		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.println(r1.get(i).get_num_pecas() + "\t\t" +
							   r2.get(i).get_num_pecas() + "\t\t" +
							   r3.get(i).get_num_pecas() + "\t\t" +
							   r4.get(i).get_num_pecas());
		}
		System.out.println();System.out.println();
	}
//	=============================================================
	
}

