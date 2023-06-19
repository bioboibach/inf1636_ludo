package modal;

import controller.*;

import view.Frame;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException{
		Jogo j = Jogo.getInstance();
		j.initialize_jogo();
		
		new Frame();
	}
}
