package modal;

/* int id_peca - acho q n precisa do id da peca
 * int id_time
 * coord posicao
 */
public class Peca {
	int id_time;
	int coord[] = new int[2];
	
	public Peca(int id, int x, int y) {
		id_time = id;
		coord[0] = x;
		coord[1] = y;
	}
	
	

}
