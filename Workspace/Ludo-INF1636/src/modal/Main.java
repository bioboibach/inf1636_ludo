package modal;
import view.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Jogo jogo = new Jogo();
		jogo.start_players();
		jogo.start_board();
		
		System.out.print(jogo.players[0].get_peca(0).get_current_tile().get_type());
//		
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print("jogador" + i + ": ");
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_type());
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_num_pecas());
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_p1());
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_p2());
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_p3());
//				System.out.print("tipo do tile q p1 ta:" + jogo.players[i].get_peca(j).get_current_tile().get_p4());
//				
//			}
//		}
////		new Frame();
//		
//		for (int i = 0; i  < 15; i++) {
//			System.out.print("| ");
//			for (int j = 0; j < 15; j++) {
//				System.out.print(Tabuleiro.getInstance().tile_matrix[i][j].get_type()+ " ");				
//			}
//			System.out.println("|");
//		}
	}
}
