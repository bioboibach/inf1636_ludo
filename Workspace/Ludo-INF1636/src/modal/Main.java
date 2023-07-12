package modal;
import view.Frame;
import view.LudoBoard;

import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException{
		Jogo j = Jogo.getInstance();
		j.initialize_jogo();
		
		new Frame();
	}
}
