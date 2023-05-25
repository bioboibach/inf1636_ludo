package modal;
import java.util.ArrayList;


class Tabuleiro {
	/* tile matriz_de_tile[15x15]
	 * 
	 */
	
	private static Tabuleiro instance;

	private Tabuleiro() {}
	
	public static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
	
}
