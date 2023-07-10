package modal;
import java.util.*;
import controller.Observavel;
import view.Observador;

class Jogo implements Observavel {    
	private static Jogo instance;
	List<Observador> observers = new ArrayList<Observador>();

	private Player players[] = new Player[4];
	private Tabuleiro t;
	private Dado d;
	
	private int 	current_peca;
	private Peca 	last_moved_peca 	= null;
	private boolean capture_flag 		= false;
	private int 	current_player 		= 0;
	private int 	current_dado 		= 5;
	private int 	qtd_6_rolados 		= 0;
	
	protected Jogo() {	
		this.addObservador(Observador.getInstance());
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
		t = Tabuleiro.getInstance();
	}
	protected void start_dado() {
		d = Dado.getInstance();
	}
	protected void start_game() {
		update_capture(false);
		set_turn(0);
		current_dado = 5;
		qtd_6_rolados = 0;
//		Inicializa as casas iniciais de cada jogador com referencias as casas inicias respectivas no tabuleiro
		for (int count = 0; count < 4; count++) {
			for (int i = 0; i < 4; i++) {
				get_player(count).get_peca(i).change_casa(t.get_casas_iniciais_index(count));
				t.get_casas_iniciais_index(count).add_peca(get_player(count).get_peca(i));
			}
		}
//		Move a primeira peca vermelha para a casa de saida
		Peca p = players[0].get_peca(0);
		p.move_to_casa_de_saida();
		update_last_moved_peca(p);
		end_turn();
	}
	protected void new_game() {
		t.clear_tabuleiro();
		start_game();
	}

//	Operacoes -------------------------------------------
	protected void turn(boolean b) {
		if (b) current_dado = d.roll();
		Player ply = players[current_player];
		Peca p;
		Casa c;
		
		// TODO
		// Notify observer a partir do uso dos dados
		System.out.println("player " + current_player + " turn");
		System.out.println("dado = " + current_dado);
		print_map();
		
		switch(current_dado) {
			case 5:

				c = t.get_casas_iniciais_index(ply.get_id());
				
	//			se tem peca na casa inicial
				if (c.get_num_pecas() != 0 && t.get_casa_de_saida(ply.get_id()).is_casa_vaga(c.get_primeira_peca_player(ply))) {
					p = c.get_primeira_peca_player(ply);
					p.move_to_casa_de_saida();
					update_last_moved_peca(p);
				}
	//			se pode mover alguma coisa
				else if (ply.can_move(current_dado)) {
					p = ply.pick_peca(current_dado);
					p.move(current_dado);
					update_last_moved_peca(p);
				}
				break;
				
			case 6:

				qtd_6_rolados++;
				if (qtd_6_rolados == 3) {
					c = last_moved_peca.get_current_casa();
					if(c.get_tipo() != 5 && c.get_tipo() != 4) {
						last_moved_peca.move_to_base();
					}
					end_turn();
					return;
				}
				
	//			se player tem barreira
				else if (ply.get_barrier() != null){
					p = ply.get_barrier();
					p.move(current_dado);
					update_last_moved_peca(p);
				}
				
				else if (ply.can_move(current_dado)){
					p = ply.pick_peca(current_dado);
					p.move(current_dado);
					update_last_moved_peca(p);
				}
				break;
	
			default:

				if (ply.can_move(current_dado)) {
					p = ply.pick_peca(current_dado);
					p.move(current_dado);
					update_current_piece(ply, p);
					update_last_moved_peca(p);
				}
		}
		
		while (last_moved_peca.get_current_casa().is_casa_final() || capture_flag == true) {
			if (capture_flag == true) update_capture(false);
			if(ply.can_move(6)) {
				p = ply.pick_peca(6);
				p.move(6);
				update_last_moved_peca(p);
			}
			else break;
		}
		
		if (check_end_game_condition() != -1) {
			end_game();
		}
		if (current_dado == 6) {
			turn(true);
		}
		end_turn();
		return;		
	}
	protected void end_turn() {
		current_player = (current_player + 1) % 4;
		qtd_6_rolados = 0;
		capture_flag = false;
	}
	protected void set_turn(int t) {
		current_player = t;
	}
	protected void next_turn() {
		current_player = (current_player + 1)%4;
	}
	protected void set_dado(int t) {
		current_dado = t;
	}


	protected int check_end_game_condition() {
		if 		(t.get_casa_final(0).get_num_pecas() == 4) return 0;
		else if	(t.get_casa_final(1).get_num_pecas() == 4) return 1;
		else if	(t.get_casa_final(2).get_num_pecas() == 4) return 2;
		else if	(t.get_casa_final(3).get_num_pecas() == 4) return 3;
		return -1;
	}
	protected void end_game() {
//		TODO
//		finaliza o jogo
//		calcula 2o 3o e 4o lugar
		System.out.print("\n\n\n ======================     FIM DE JOGO    ======================\n\n\n");
		
	}
	protected int[] define_podio() {
		TreeSet<Integer> pecas_count = new TreeSet<>();
		List<Integer> pecas_count_lst = new ArrayList<>(pecas_count);
		int[] podio = new int[4]; 		//Colocacao de cada player
		
		for(int i = 0; i < 4; i++)
			pecas_count.add(t.get_casa_final(i).get_num_pecas());
		
		for(int i = 0; i < 4; i++)
			podio[i] = pecas_count_lst.indexOf(t.get_casa_final(i).get_num_pecas());
		
		return podio;
	}
	
	protected void update_current_piece(Player ply, Peca p) {
		int i;
		for (i = 0; i < 4; i++) {
			if (ply.get_peca(i).equals(p)) break;
		}
		current_peca = (p.get_cor() * 4) + i; 
	}
	
	protected void update_last_moved_peca(Peca p) {
		last_moved_peca = p;
	}
	
	protected void captura(Casa c) {
		Peca p = c.get_peca(0);
		if (p == null) {
			p = c.get_peca(1);
		}
		p.move_to_base();
		update_capture(true);
	}
	protected void update_capture(boolean b) {
		capture_flag = b;
	}
	

//	Metodos get ----------------------------------------
	protected Peca get_last_moved_piece() {
		return last_moved_peca;
	}
	protected Player get_player(int id){
		return players[id];
	}
	protected int get_turn() {
		return current_player;
	}
	protected int get_val_dado() {
		return current_dado;
	}

	
//	Metodos Auxiliares ---------------------------------
	
//	TODO remover==============================================
	protected void print_map() {
		ArrayList<Casa> map = t.get_path();
		ArrayList<Casa> r1 = t.get_r1();
		ArrayList<Casa> r2= t.get_r2();
		ArrayList<Casa> r3 = t.get_r3();
		ArrayList<Casa> r4 = t.get_r4();
		ArrayList<Casa> ini = t.get_ini();
		
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
	
//	Implementacao da interface Observer ----------------
	public void addObservador(Observador o) {
		observers.add(o);
	}
	public void removeObservador(Observador o) {
		observers.remove(o);
	}
    public void atualizaObservadores() {
        ListIterator<Observador> li = observers.listIterator();
        while(li.hasNext()) {
        	li.next().notify(this);
        }
    }
    public Object[] get() {
    	Object dados[] = new Object[6];
    	dados[0] = Integer.valueOf(current_peca);
    	dados[1] = Integer.valueOf(current_player);
    	dados[2] = Integer.valueOf(current_dado);
    	dados[3] = define_podio();
    	dados[4] = Integer.valueOf(Tabuleiro.getInstance().get_index_current_casa(players[current_player].get_peca(current_peca))[0]);
    	dados[5] = Integer.valueOf(Tabuleiro.getInstance().get_index_current_casa(players[current_player].get_peca(current_peca))[1]);
    	return dados;
    }
    
//	Singleton ------------------------------------------
	protected static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
}

