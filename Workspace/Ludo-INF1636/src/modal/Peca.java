package modal;

class Peca {
	private int id_time;
	private Casa current_casa = null;
	
	protected Peca(int id) {
		id_time = id;
	}
	
//	Operacoes -------------------------------------------
	
//	TODO
//	funcao encarregada de movimentar a peca
//	verifica onde a peca ta, pra onde vai, se pode mover e atualiza
	protected void move(int die_val) {
		Tabuleiro t = Tabuleiro.getInstance();
		if (t.get_path_current_casa(this) == -1) return;
		Casa c = t.get_destination(t.get_path_current_casa(this), die_val, this, true);
		current_casa.remove_peca(this);
		current_casa = c;
		current_casa.add_peca(this);
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
	protected void move_to_base() {
		current_casa.remove_peca(this);
		current_casa = Tabuleiro.getInstance().get_casas_iniciais_index(id_time);
		current_casa.add_peca(this);
	}
	protected void move_to_casa_de_saida() {
		current_casa.remove_peca(this);
		if 		(id_time == 0) current_casa = Tabuleiro.getInstance().get_path_index(0);
		else if (id_time == 1) current_casa = Tabuleiro.getInstance().get_path_index(13);
		else if (id_time == 2) current_casa = Tabuleiro.getInstance().get_path_index(26);			
		else if (id_time == 3) current_casa = Tabuleiro.getInstance().get_path_index(39);
		current_casa.add_peca(this);
	}
	protected void move_to_reta_final(int index) {
		current_casa.remove_peca(this);
		Tabuleiro.getInstance().get_reta_final_index(index, id_time);
		current_casa.add_peca(this);
	}
	
	protected void change_casa(Casa c) {
		current_casa = c;
	}

//	Verificacoes ---------------------------------------
	protected boolean can_move(int val_die) {
		Tabuleiro t = Tabuleiro.getInstance();
		Casa c;
		int[] i = new int[2];
		int count;
		i = t.get_index_current_casa(this);
		
		if (t.is_in_path(this)) {
			return t.check_path(i[0], val_die, this);
		}
		
		else if (current_casa.is_casa_inicial()) {
			c = t.get_casa_de_saida(id_time);
			return c.is_casa_vaga(this);
		}
		
		else if (current_casa.is_casa_final()) {
			return false;
		}
		
		else if (current_casa.is_reta_final()) {
			c = t.get_destination(i[0], val_die, this, false);
			if (c == null) return false;
			return c.is_casa_vaga(this);
		}
		return true;
	}

//	Metodos get ----------------------------------------
	protected Casa get_current_casa() {
		return current_casa;
	}
	protected int get_cor() {
		return id_time;
	}
	
}
