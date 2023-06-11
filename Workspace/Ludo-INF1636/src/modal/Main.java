package modal;

import view.*;

public class Main {
	public static void main(String[] args) {
		Jogo j = Jogo.getInstance();
		j.initialize_jogo();
		j.turn();
		
		new Frame();
	}
}
