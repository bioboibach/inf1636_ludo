package modal;

public class ModalAPI {
	private static ModalAPI instance;
	
	private Jogo jogoInst = Jogo.getInstance();
	
	private ModalAPI() {}
	
//	Operacoes -------------------------------------------
	public void set_positions(int player_id, int peca_id, int index, int list_id) {
//		TODO: DESCOBRIR O ID DA PECA AQUI
		peca_id = 1;
		
		Peca p = jogoInst.get_player(player_id).get_peca(peca_id);
		Casa c = null;
//		path
		if (list_id == 0) {
			c = Tabuleiro.getInstance().get_path_index(index);
		}
//		casa inicial
		else if (list_id == 1) {
			c = Tabuleiro.getInstance().get_casas_iniciais_index(player_id);
		}
		else {
			c = Tabuleiro.getInstance().get_reta_final_index(index, list_id - 2);
		}
		c.add_peca(p);
		p.change_casa(c);
<<<<<<< HEAD:Workspace/Ludo-INF1636/src/modal/ModalAPI.java
		jogoInst.update_current_piece(jogoInst.get_player(p.get_cor()), p);
		notify();
	}
	public void run_turn(boolean t) {
		jogoInst.turn(t);
=======
		Jogo.getInstance().update_current_piece(Jogo.getInstance().get_player(p.get_cor()), p);
	}
	public void run_turn() {
		Jogo.getInstance().turn();
>>>>>>> a17d2e70308331790844cf6ded05e8b0063d1660:Workspace/Ludo-INF1636/src/modal/Modal_interface.java
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

//	Metodos get ----------------------------------------
	public Casa get_path_index(int index){
		return Tabuleiro.getInstance().get_path_index(index);
	}
	public Casa get_casas_iniciais_index(int index){
		return Tabuleiro.getInstance().get_casas_iniciais_index(index);
	}
	public Casa get_reta_final_vermelho_index(int index){
		return Tabuleiro.getInstance().get_reta_final_index(index, 0);
	}
	public Casa get_reta_final_verde_index(int index){
		return Tabuleiro.getInstance().get_reta_final_index(index, 1);
	}
	public Casa get_reta_final_amarelo_index(int index){
		return Tabuleiro.getInstance().get_reta_final_index(index, 2);
	}
	public Casa get_reta_final_azul_index(int index){
		return Tabuleiro.getInstance().get_reta_final_index(index, 3);
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