package modal;

class Peca {
	private int id_time;
	private Casa current_tile = null;
	
	protected Peca(int id) {
		id_time = id;
	}
	
//	TODO
//	funcao encarregada de movimentar a peca
//	verifica onde a peca ta, pra onde vai, se pode mover e atualiza
	protected void move(int die_val) {
		return;
//		Jogo j = Jogo.getInstance();
//		
////		se esta na casa inicial
//		if (current_tile.get_tipo() == 1) {
//			current_tile.casa_vaga(this);
//			this.move_to_casa_de_saida();
//		}
//		
////		se esta na casa de entrada
//		else if (current_tile.get_tipo() == 6) {
//			
//			this.move_to_reta_fianl(die_val);
//		}
//		
//		if (Tabuleiro.getInstance().check_path(die_val, this)) {
//			
//		}
	}
//	
	protected boolean can_move(int val_die) {
		return true;
		
//		int[] i = new int[2];
//		int count;
//		i = Tabuleiro.getInstance().get_index_current_tile(this);
////		se ta na path
//		if (i[1] == 0) {
//			return Tabuleiro.getInstance().check_path(i[0], val_die, this);
//		}
//		else if (i[1] == 1) {
//			Tabuleiro.getInstance().check_path(i[0], val_die, this);
//		}
	}
//	
	protected void move_to_base() {
		current_tile.remove_peca(this);
		current_tile = Tabuleiro.getInstance().get_casas_iniciais_index(id_time);
		current_tile.add_peca(this);
	}
	protected void move_to_casa_de_saida() {
		current_tile.remove_peca(this);
		if (id_time == 0) current_tile = Tabuleiro.getInstance().get_path_index(0);
		else if (id_time == 1) current_tile = Tabuleiro.getInstance().get_path_index(13);
		else if (id_time == 2) current_tile = Tabuleiro.getInstance().get_path_index(26);			
		else if (id_time == 3) current_tile = Tabuleiro.getInstance().get_path_index(39);
		current_tile.add_peca(this);
	}
	protected void move_to_reta_fianl(int index) {
		current_tile.remove_peca(this);
		if (id_time == 0) current_tile = Tabuleiro.getInstance().get_reta_final_vermelho_index(index);
		else if (id_time == 1) current_tile = Tabuleiro.getInstance().get_reta_final_verde_index(index);
		else if (id_time == 2) current_tile = Tabuleiro.getInstance().get_reta_final_amarelo_index(index);			
		else if (id_time == 3) current_tile = Tabuleiro.getInstance().get_reta_final_azul_index(index);
		current_tile.add_peca(this);
	}
	
	protected Casa get_current_tile() {
		return current_tile;
	}

	protected int get_cor() {
		return id_time;
	}
	
	protected void change_casa(Casa c) {
		current_tile = c;
	}

	
}
