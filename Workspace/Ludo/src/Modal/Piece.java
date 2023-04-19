package Modal;


/* 
 * =======  stack_up()  =======
 * atualiza a variavel stacked das duas pecas para true
 * 
 * 
 * =======  stack_down()  =======
 * atualiza a variavel stacked das duas pecas para false
 * 
 * 
 * =======  is_stacked()  =======
 * retorna true se a pecas estiver empilhada
 * retorna false se a peca nao estiver empilhada
 * 
 * 
 * =======  get_color()  =======
 * retorna a cor da peca
 * 
 * 
 * =======  get_id()  =======
 * retorna o id da peca
 * 
 * 
 *  =======  get_stacked_ids()  =======
 *  retorna o id das duas pecas stackadas
 */


public class Piece {
	int id; //numero da peca
	int color; //em teoria n precisa, da pra pegar pelo id
	boolean stacked = false; //todas as pecas comecam individuais
	
	public Piece (int id, int color) {
		this.color = color;
		this.id = id;
		return;
	}
	
	public void stack_up(Piece p) {
		stacked = true;
		p.stacked = true;
		return;
	}
	
	public void stack_down(Piece p) {
		stacked = false;
		p.stacked = false;
		return;
	}
	
	public int get_color() {
		return color;
	}
	
	public int get_id() {
		return id;
	}
	
	public boolean is_stacked() {
		return stacked;
	}
	
	public int[] get_stacked_ids(Piece p1, Piece p2) {
		int[] ids = new int[2];
		ids[0] = p1.get_id();
		ids[1] = p2.get_id();
		return ids;
		
	}
}
