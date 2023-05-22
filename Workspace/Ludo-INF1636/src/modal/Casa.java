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
	
	int coord[] = new int[2];
	int type;
	int cor;
	Casa next = null;
	int num_pieces = 0;
	Peca p1 = null;
	Peca p2 = null;

	protected Casa(int x, int y) {
		
//		casa inicial vermelha
		if ((x == 1 || x == 4) &&
			(y == 1 || y == 4)) {
			type = 1;
			cor = 0;
		}
		
//		casa inicial verde
		else if ((x == 10 || x == 13) &&
			(y == 1 || y == 4)) {
			type = 1;
			cor = 1;
		}
		
//		casa inicial amarela
		else if ((x == 10 || x == 13) &&
			(y == 10 || y == 13)) {
			type = 1;
			cor = 2;
		}
		
//		casa inicial azul
		else if ((x == 1 || x == 4) &&
			(y == 10 || y == 13)) {
			type = 1;
			cor = 3;
		}
		
//		casa de saida
		else if ((x == 1 && y == 8)  ||
				 (x == 8 && y == 13) ||
			 	 (x == 6 && y == 1)  ||
				 (x == 13 && y == 6)) {
			type = 2;
		}
		
//		casa de abrigo
		else if ((x == 1 && y == 6)  ||
				 (x == 6 && y == 13) ||
				 (x == 8 && y == 1)  ||
				 (x == 13 && y == 8)) {
			type = 3;
		}
	}
	
	protected int get_type() {
		return type;
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
