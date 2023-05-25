package modal;
import view.*;

public class Main {

	public static void main(String[] args) {
		Jogo j = Jogo.getInstance();
	
		for (int i = 0; i <= 51; i++) {
			System.out.print(j.path.get(i).get_type() + ", ");
		}
		System.out.println();
		for (int i = 0; i <= 51; i++) {
			System.out.print(j.path.get(i).get_cor() + ", ");
		}
		
//		new Frame();
	}
}
