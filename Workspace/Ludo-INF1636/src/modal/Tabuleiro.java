package modal;
import java.util.ArrayList;


class Tabuleiro {
	
	private static Tabuleiro instance;

	private Tabuleiro() {}
	
	protected static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
	
}
