package modal;


/*	int num_pecas na tile
 * 	int tipo_da_casa
 * 
 * 
 * type -> indica tipo do tile:
 * 		-> 0 = casa comum
 * 		-> 1 = casa inicial
 * 		-> 2 = casa de saida
 * 		-> 3 = abrigo
 * 		-> 4 = reta final
 * 		-> 5 = final
 * 
 * cor  -> 0 = vermelho
 * 		-> 1 = verde
 * 		-> 2 = amarelo
 * 		-> 3 = azul
 * */
class Casa {
	
	protected int coord[] = new int[2];
	protected int type;
	protected int cor;
	protected Casa next = null;
	protected int num_pecas = 0;
	protected Peca p1 = null;
	protected Peca p2 = null;
	protected Peca p3 = null;
	protected Peca p4 = null;
	
	protected Casa() {}
	
	
	protected Casa(int type, int id) {
		this.type = type;
		cor = id;		
	}
	
	protected Casa(int type) {
		this.type = type;
	}
//	
////	deprecated
//	protected Casa(int x, int y) {
//		
////		casa inicial vermelha
//		if ((x == 1 || x == 4) &&
//			(y == 1 || y == 4)) {
//			type = 1;
//			cor = 0;
//		}
//		
////		casa inicial verde
//		else if ((x == 10 || x == 13) &&
//			(y == 1 || y == 4)) {
//			type = 1;
//			cor = 1;
//		}
//		
////		casa inicial amarela
//		else if ((x == 10 || x == 13) &&
//			(y == 10 || y == 13)) {
//			type = 1;
//			cor = 2;
//		}
//		
////		casa inicial azul
//		else if ((x == 1 || x == 4) &&
//			(y == 10 || y == 13)) {
//			type = 1;
//			cor = 3;
//		}
//		
////		casa de saida
//		else if ((x == 1 && y == 8)  ||
//				 (x == 8 && y == 13) ||
//			 	 (x == 6 && y == 1)  ||
//				 (x == 13 && y == 6)) {
//			type = 2;
//		}
//		
////		casa de abrigo
//		else if ((x == 1 && y == 6)  ||
//				 (x == 6 && y == 13) ||
//				 (x == 8 && y == 1)  ||
//				 (x == 13 && y == 8)) {
//			type = 3;
//		}
//	}
//	
	/*casa_vaga(Peca p):
	 * se for casa de final 	-> true
	 * se reta final e chega em casa de reta final -> false
	 * se n tem peca 			-> true
	 * 
	 * se tem 1 peca da msm cor	
	 * 		se é casa de saida	-> false
	 * 		else				-> true 
	 * se tem 1 peca
	 * */
	protected boolean casa_vaga(Peca p) {
		if (type == 5) return true;
		else if (p.get_current_tile().type == 4 && this.type == 4) return false;
		
		else if (num_pecas == 0) return true;
		else if(num_pecas == 2) return false;
		
		else if (num_pecas == 1 && p.get_cor() == p1.get_cor())
//			se casa de saída
			if (type == 2) return false;
			else return true;

		return false;
	}
	
	protected boolean is_barreira() {
		if (num_pecas < 2) return false;
		else if (p1.get_cor() == p2.get_cor()) return true;
		return false;
	}
	
	protected void remove_peca(Peca p) {
		num_pecas--;
		p.change_casa(null);
		if (p.equals(p1)) p1 = null;			
		else p2 = null;
	}
	
//	assumo que a casa esta livre
	protected void add_peca(Peca p) {
		num_pecas++;
		p.change_casa(this);
		if (p1 == null) p1 = p;
		else if (p2 == null) p2 = p;
		else if (p3 == null) p3 = p;
		else if (p4 == null) p4 = p;
	}
	
	protected int get_type() {
		return type;
	}
	
	protected int get_num_pecas() {
		return num_pecas;
	}
	
	protected Peca get_p1() {
		return p1;
	}
	protected Peca get_p2() {
		return p2;
	}
	protected Peca get_p3() {
		return p3;
	}
	protected Peca get_p4() {
		return p4;
	}
	
	
	
	protected boolean is_casa_comum() {
		if (type == 0) return true;
		return false;
	}
	
	protected boolean is_casa_inicial() {
		if (type == 1) return true;
		return false;
	}
	
	protected boolean is_casa_de_saida() {
		if (type == 2) return true;
		return false;
	}	
	
	protected boolean is_abrigo() {
		if (type == 3) return true;
		return false;
	}
	
	protected boolean is_reta_final() {
		if (type == 4) return true;
		return false;
	}
	
	protected boolean is_casa_de_entrada() {
		if (type == 5) return true;
		return false;
	}
	
}
