package modal;
import view.*;

public class Main {

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.start_players();
		jogo.start_board();
		
		new Frame();
		
		for (int i = 0; i  < 15; i++) {
			System.out.print("| ");
			for (int j = 0; j < 15; j++) {
				System.out.print(Tabuleiro.getInstance().tile_matrix[i][j].get_type()+ " ");				
			}
			System.out.println("|");
		}
	}
}
