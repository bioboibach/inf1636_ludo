package modal;

/* int id_peca - acho q n precisa do id da peca
 * int id_time
 * coord posicao
 */
class Peca {
	private static int id_peca;
	private int id_time;
	private Casa current_tile = null;
	
	protected Peca(int id) {
		id_time = id;

	}
	
//	protected void move_to_base() {
//		Casa c = ;
//		current_tile = c.get_casa_inicial(id_time);
//	}
	
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
