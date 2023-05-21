package modal;

class Jogo {
	Tabuleiro t;
	Player players[] = new Player[4];
	
	public Jogo() {}
	
	
	public void start_players() {		
		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i);
		}
	}
	
	public void start_board() {
//		t = Tabuleiro.getInstance();
		
		
	}
	
	protected Player get_player(int id){
		return players[id];
	}
	
}

