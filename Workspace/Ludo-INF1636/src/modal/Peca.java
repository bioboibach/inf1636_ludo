package modal;

/* int id_peca - acho q n precisa do id da peca
 * int id_time
 * coord posicao
 */
class Peca {
	private int id_time;
	private Casa current_tile = null;
	
	protected Peca(int id) {
		id_time = id;

	}
	
	protected void move(int die_val) {
		Jogo j = Jogo.getInstance();
		
		j.check_path(die_val, this);
	}
	
	protected void move_to_base() {
		current_tile = Jogo.getInstance().get_casas_iniciais().get(id_time);
		current_tile.add_peca(this);
	}
	
	protected void move_to_reta_fianl(int index) {
		if (id_time == 0) current_tile = Jogo.getInstance().get_reta_final_vermelho().get(index);
		else if (id_time == 1) current_tile = Jogo.getInstance().get_reta_final_verde().get(index);
		else if (id_time == 2) current_tile = Jogo.getInstance().get_reta_final_amarelo().get(index);			
		else if (id_time == 3) current_tile = Jogo.getInstance().get_reta_final_azul().get(index);
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
