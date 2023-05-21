package modal;

/* int id_peca - acho q n precisa do id da peca
 * int id_time
 * coord posicao
 */
class Peca {
	int id_time;
	Casa current_tile = null;
	
	public Peca(int id) {
		id_time = id;
	}
	
	public Casa get_current_tile() {
		return current_tile;
	}

	public int get_id() {
		return id_time;
	}
}
