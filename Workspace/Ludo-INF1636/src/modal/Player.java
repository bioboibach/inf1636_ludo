package modal;

class Player {
	/* peca arr_peca
	 * int id_player
	 * 
	 * */
	
	int player_id;
	Peca pecas[] = new Peca[4];
	
	protected Player(int id) {
		player_id = id;
		for (int count = 0; count < 4; count++) {
			pecas[count] = new Peca(id);
		}
	}
	
	protected Peca get_peca(int id) {
		return pecas[id];
	}
	
	
}
