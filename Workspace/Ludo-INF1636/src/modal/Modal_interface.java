package modal;


public class Modal_interface {
	private static Modal_interface instance;
	
	private Modal_interface() {}
	
//	Operacoes -------------------------------------------
	public void set_positions(int player_id, int peca_id, int index, int list_id) {
		Peca p = Jogo.getInstance().get_player(player_id).get_peca(peca_id);
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
		Jogo.getInstance().update_current_piece(Jogo.getInstance().get_player(p.get_cor()), p);
		notify();
	}
	public void run_turn(boolean t) {
		Jogo.getInstance().turn(t);
	}
	public void set_turn(int t) {
		Jogo.getInstance().set_turn(t);
	}
	
	public void set_dado(int t) {
		Jogo.getInstance().set_dado(t);
	}
	public void clear_tabuleiro() {
		Tabuleiro.getInstance().clear_tabuleiro();
	}
	public void new_game() {
		Jogo.getInstance().new_game();
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
		return Jogo.getInstance().get_turn(); 
	}
	public int[] get_peca_indexes(int player_id, int peca_id) {
		Peca p = Jogo.getInstance().get_player(player_id).get_peca(peca_id);
		return Tabuleiro.getInstance().get_index_current_casa(p);
	}
	public int get_roll() {
		return Jogo.getInstance().get_val_dado();
	}
	
//	Singleton ------------------------------------------
	public static Modal_interface getInstance() {
		if (instance == null) {
			instance = new Modal_interface();
		}
		return instance;
	}
}
