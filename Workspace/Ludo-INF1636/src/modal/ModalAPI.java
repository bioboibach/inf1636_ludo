package modal;

public class ModalAPI {
	private static ModalAPI instance;
	private Jogo jogoInst = Jogo.getInstance();
	private Tabuleiro tabuleiroInst = Tabuleiro.getInstance();
// ____________________________________________________________________________________________________________________________
//
	private ModalAPI() {}
	
	//	Operacoes -------------------------------------------	
	public void run_turn(int pathIndex, int finalPathIndex, int diceVal) {
		jogoInst.initializeTurn(pathIndex, finalPathIndex, diceVal);
		jogoInst.turn();
	}

	public void clear_tabuleiro() {
		Tabuleiro.getInstance().clear_tabuleiro();
	}
	public void new_game() {
		jogoInst.new_game();
	}

	public int roll() {
		return Dado.getInstance().roll();
	}

	//	SET ----------------------------------------
//	public void set_positions(int playerIndex, int listIndex, int listType) {
//		Peca p = jogoInst.get_player(playerIndex).get_peca(listIndex, listType);
//		Casa c = null;
//		
//		if (listType == 0) {		//	path
//			c = tabuleiroInst.get_pathIndex(listIndex);
//		}
//		else if (listType == 1) {	//	casa inicial
//			c = tabuleiroInst.get_casasIniciaisIndex(playerIndex);
//		}
//		else {						//	final path
//			c = tabuleiroInst.get_retaFinalIndex(listIndex, playerIndex);
//		}
//		c.add_peca(p);
//		p.change_casa(c);
//		
//
//		jogoInst.set_currentPeca(jogoInst.get_player(p.get_cor()), p);
//		notify();
//	}

	public void set_turn(int t) {
		jogoInst.set_turn(t);
	}
	public void set_dice(int t) {
		jogoInst.set_dice(t);
	}

	
	//	GET ----------------------------------------
	public Casa get_path_index(int index){
		return Tabuleiro.getInstance().get_pathIndex(index);
	}
	public Casa get_casas_iniciais_index(int index){
		return Tabuleiro.getInstance().get_casasIniciaisIndex(index);
	}
	public Casa get_reta_final_vermelho_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 0);
	}
	public Casa get_reta_final_verde_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 1);
	}
	public Casa get_reta_final_amarelo_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 2);
	}
	public Casa get_reta_final_azul_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 3);
	}
	
	public int get_player_turn() {
		return jogoInst.get_turn(); 
	}
	public int[] get_peca_indexes(int player_id, int peca_id) {
		Peca p = jogoInst.get_player(player_id).get_peca(peca_id);
		return Tabuleiro.getInstance().get_currentCasaIndex(p);
	}
	public int get_roll() {
		return jogoInst.get_currentDice();
	}
	
	//	Singleton ------------------------------------------
	public static ModalAPI getInstance() {
		if (instance == null) {
			instance = new ModalAPI();
		}
		return instance;
	}
}