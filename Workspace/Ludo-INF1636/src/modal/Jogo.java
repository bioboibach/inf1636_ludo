package modal;

class Jogo {
	{
	Player players[] = new Player[4];
	Tabuleiro t = Tabuleiro.getInstance();
	
	
	for (int i = 0; i < 4; i++) {
		players[i] = new Player(i);
	}
	}
}