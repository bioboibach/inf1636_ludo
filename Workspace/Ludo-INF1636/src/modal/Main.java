package modal;

import view.*;

public class Main {

	public static void main(String[] args) {
		Jogo j = Jogo.getInstance();
		j.start_game();		
		
		new Frame();
	}
}
