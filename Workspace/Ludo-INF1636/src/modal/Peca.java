package modal;

class Peca {
	private int id_time;
	private Casa current_casa = null;
	
	protected Peca(int id) {
		id_time = id;
	}
	
	//
//	protected void move(int die_val) {
//		Tabuleiro t = Tabuleiro.getInstance();
//		Casa c;
//		if (t.is_in_path(this)) {
//			c = t.get_destination(t.get_path_current_casa(this), die_val, this, true);
//
//			if (c.get_num_pecas() > 0) {
//				int cor;
//				if (c.get_peca(0) == null)
//					cor = c.get_peca(1).get_cor();
//				else
//					cor = c.get_peca(0).get_cor();
//
//				if (cor != id_time && !c.is_abrigo() && !c.is_casa_de_saida()
//						&& (c.is_casa_de_saida() && c.get_cor() != cor)) {
//					Jogo.getInstance().captura(c);
//				}
//			}
//		}
//		else if (t.is_in_reat_final(this))
//			c = t.get_destination(t.get_reta_final_current_casa(this), die_val, this, false);
//		else return;
//		current_casa.remove_peca(this);
//		current_casa = c;
//		current_casa.add_peca(this);
//
//	}

	
//	Operacoes -------------------------------------------
	
//	TODO
//	funcao encarregada de movimentar a peca
//	verifica onde a peca ta, pra onde vai, se pode mover e atualiza-la
	protected void move(int die_val) {
		Tabuleiro t = Tabuleiro.getInstance();
		
		if (t.get_path_current_casa(this) == -1) return;
		
		Casa c = t.get_destination(t.get_path_current_casa(this), die_val, this, true);
		
		if (c.get_num_pecas() > 0) {
			int cor;
			if (c.get_peca(0) == null) cor = c.get_peca(1).get_cor(); 
			else cor = c.get_peca(0).get_cor();
			
			if (cor != id_time && !c.is_abrigo() && !c.is_casa_de_saida() && (c.is_casa_de_saida() && c.get_cor() != cor)) {
				Jogo.getInstance().captura(c);
			}
		}
		current_casa.remove_peca(this);
		current_casa = c;
		current_casa.add_peca(this);
	}
	
//	protected void move(int die_val) {
//		Tabuleiro t = Tabuleiro.getInstance();
//		Casa c;
//		if (t.is_in_path(this)) {
//			c = t.get_destination(t.get_path_current_casa(this), die_val, this, false);
//
//			if (c.get_num_pecas() > 0) {
//				int cor;
//				if (c.get_peca(0) == null)
//					cor = c.get_peca(1).get_cor();
//				else
//					cor = c.get_peca(0).get_cor();
//
//				if (cor != id_time && !c.is_abrigo() && !c.is_casa_de_saida()
//						&& (c.is_casa_de_saida() && c.get_cor() != cor)) {
//					Jogo.getInstance().captura(c);
//				}
//			}
//			current_casa.remove_peca(this);
//			current_casa = c;
//			current_casa.add_peca(this);
//		}
//		else if (t.is_in_reat_final(this)) {
//			c = t.get_destination(t.get_reta_final_current_casa(this), die_val, this, true);
//			current_casa.remove_peca(this);
//			current_casa = c;
//			current_casa.add_peca(this);
//		}
//	}
	
	protected void move_to_base() {
		current_casa.remove_peca(this);
		current_casa = Tabuleiro.getInstance().get_casas_iniciais_index(id_time);
		current_casa.add_peca(this);
	}
	protected void move_to_casa_de_saida() {
		current_casa.remove_peca(this);
		current_casa = Tabuleiro.getInstance().get_casa_de_saida(this.get_cor());
		current_casa.add_peca(this);
	}
	protected void move_to_reta_final(int index) {
		current_casa.remove_peca(this);
		current_casa = Tabuleiro.getInstance().get_reta_final_index(index, id_time);
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
			if (c == null) {
				return false;
			}
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
