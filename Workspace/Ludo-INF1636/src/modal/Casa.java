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
 * */
class Casa {
	
	int coord[] = new int[2];
	int type;
	int num_pieces = 0;
	Peca p1 = null;
	Peca p2 = null;

	public Casa(int x, int y) {
		
//		casa inicial
		if ((coord[0] == 1 || coord[0] == 4) &&
			(coord[1] == 1 || coord[1] == 4)) {
			type = 1;
			
		}
		
		if ((coord[0] == 1 || coord[0] == 4 || coord[0] == 10 || coord[0] == 13) &&
			(coord[1] == 1 || coord[1] == 4 || coord[1] == 10 || coord[1] == 13)) {
			type = 1;
		}
		
//		casa de saida
		else if ((coord[0] == 1 && coord[1] == 8)  ||
				 (coord[0] == 8 && coord[1] == 13) ||
			 	 (coord[0] == 6 && coord[1] == 1)  ||
				 (coord[0] == 13 && coord[1] == 6)) {
			type = 2;
		}
		
//		casa de abrigo
		else if ((coord[0] == 1 && coord[1] == 6)  ||
				 (coord[0] == 6 && coord[1] == 13) ||
				 (coord[0] == 8 && coord[1] == 1)  ||
				 (coord[0] == 13 && coord[1] == 8)) {
			type = 3;
		}
		
//		
//		coord[0] = x;
//		coord[1] = y;
//		if (this.is_casa_inicial()) {
//			p1 = p;
//			p.coord[0] = x;
//			p.coord[1] = y;			
//			type = 1;
		}
	
	public boolean is_casa_inicial() {
		if (type == 1) return true;
		return false;
	}
	
	public boolean is_casa_de_saida() {
		if (type == 1) return true;
		return false;
	}
	
	public boolean is_casa_comum() {
		if (type == 1) return true;
		return false;
	}
	
	public boolean is_abrigo() {
		if (type == 1) return true;
		return false;
	}
	
	public boolean is_reta_final() {
		if (type == 1) return true;
		return false;
	}
	
	public boolean is_casa_de_entrada() {
		if (type == 1) return true;
		return false;
	}
}
