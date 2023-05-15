package modal;

public class Player {
	/* peca arr_peca
	 * int id_player
	 * 
	 * */
	
	int player_id;
	Peca pecas[] = new Peca[4];
	
	public Player(int id, Peca p1, Peca p2, Peca p3, Peca p4) {
		player_id = id;
		for (int count = 0; count < 4; count++) {
			pecas[count] = new Peca(id, -1, -1); 
		}
	}
}
