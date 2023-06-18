package modal;

import view.*;
import controller.*;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException{
		Jogo j = Jogo.getInstance();
		j.initialize_jogo();
		j.turn();

		Controller_interface.getInstance().load_game();

//		new Frame();
	}
}
