package modal;

public class ModalAPI {
	private static ModalAPI instance;
	private Jogo jogoInst = Jogo.getInstance();
	
// ____________________________________________________________________________________________________________________________
//
	private ModalAPI() {}
	
	//	Operacoes -------------------------------------------
	public void set_positions(int playerIndex, int listIndex, int listType) {
//		TODO: DESCOBRIR O ID DA PECA AQUI
		int peca_id = 1;
		
		Peca p = jogoInst.get_player(playerIndex).get_peca(listIndex, listType);
		Casa c = null;
		//	path
		if (listType == 0) {
			c = Tabuleiro.getInstance().get_pathIndex(listIndex);
		}
		//	casa inicial
		else if (listType == 1) {
			c = Tabuleiro.getInstance().get_casasIniciaisIndex(playerIndex);
		}
		else {
			c = Tabuleiro.getInstance().get_retaFinalIndex(listIndex, listType - 2);
		}
		c.add_peca(p);
		p.change_casa(c);
		

		jogoInst.update_current_piece(jogoInst.get_player(p.get_cor()), p);
		notify();
	}
	
	public void run_turn() {
		jogoInst.turn();
	}

	public void set_turn(int t) {
		jogoInst.set_turn(t);
	}
	public void next_turn() {
		jogoInst.next_turn();
	}
	public void set_dado(int t) {
		jogoInst.set_dado(t);
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
		return Tabuleiro.getInstance().get_index_current_casa(p);
	}
	public int get_roll() {
		return jogoInst.get_val_dado();
	}
	
	//	Singleton ------------------------------------------
	public static ModalAPI getInstance() {
		if (instance == null) {
			instance = new ModalAPI();
		}
		return instance;
	}
}