package modal;


/*	int num_pecas na tile
 * 	int tipo_da_casa
 * 
 * 
 * tipo -> indica tipo do tile:
 * 		-> 0 = casa comum
 * 		-> 1 = casa inicial
 * 		-> 2 = casa de saida
 * 		-> 3 = casa abrigo
 * 		-> 4 = casa da reta final
 * 		-> 5 = casa final
 * 		-> 6 = casa de entrada
 * 
 * cor  -> 0 = vermelho
 * 		-> 1 = verde
 * 		-> 2 = amarelo
 * 		-> 3 = azul
 * 	   -> -1 = neutro
 * */
class Casa {
	
	private int tipo = 0;
	private int cor = -1;
	private int num_pecas = 0;
	private Peca[] peca_arr = new Peca[4]; 
	
	protected Casa(int tipo, int id) {
		this.tipo = tipo;
		cor = id;		
	}
	
	protected Casa(int tipo) {
		this.tipo = tipo;
	}

	/*casa_vaga(Peca p):
	 * se for casa de final(tipo = 5) 	-> true
	 * se reta final(tipo = 4) e chega em casa de reta final -> false
	 * se nao tem peca 			-> true
	 * se tem 2 pecas			-> false
	 * 
	 * se tem 1 peca da msm cor	
	 * 		se é casa de saida(2) -> false
	 * 		se nao for			-> true 
	 * 
	 * */
	protected boolean casa_vaga(Peca p) {
//		se casa de final
		if (tipo == 5) return true;
		
//		se ta na reta final e vai para outra casa da reta final (nao tirou o numero exato para chegar no final)
		else if (p.get_current_tile().tipo == 4 && this.tipo == 4) return false;

//		se tem 2 pecas na casa
		else if(num_pecas == 2) return false;
		
//		se tem 1 peca na casa e a cor dessa peca for igual a movimentada
		else if (num_pecas == 1 && p.get_cor() == peca_arr[0].get_cor()) {
//			se casa de saída
			if (tipo == 2) return false;
			else return true;
		}
//		se tem 1 ou 0 pecas, se tem mais de 2 (casa inicial e final), 
		return true;
	}
	
	protected boolean is_barreira() {
		if (num_pecas < 2) return false;
		else if (peca_arr[0].get_cor() == peca_arr[1].get_cor()) return true;
		return false;
	}
	
	protected void remove_peca(Peca p) {
		num_pecas--;
		for (int i = 0; i < 4; i++) {
			if (p.equals(peca_arr[i])) peca_arr[i] = null;
		}
	}
	
//	assumo que pode mover
	protected void add_peca(Peca p) {
		num_pecas++;
		for (int i = 0; i < 4; i++) {
			if (peca_arr[i] == null) peca_arr[i] = p;
		}
	}
	
//	TODO
//	update a casa para o vetor estar sempre preenchido em ordem crescente
//	protected void update_casa() {
//		if (num_pecas == 0) return;
//		else {
//			int index1 = 0;
//			int index2 = 3;
//			while(index1 != index2) {
////				se tem peca, anda o index1
//				if (this.get_peca(index1) != null) {
//					index1++;
//				}
//				else if (this.get_peca(index2) == null) {
//					index2--;	
//				}
//				else if() {
//					
//				}
//			}
//		}
//	}
	
	protected int get_cor() {
		return cor;
	}
	protected int get_tipo() {
		return tipo;
	}
	protected int get_num_pecas() {
		return num_pecas;
	}
	protected Peca get_peca(int index) {
		return peca_arr[index];
	}
	
	protected Peca get_primeira_peca_player(Player ply) {
		int player_cor = ply.get_id();
		for (int i = 0; i < 4; i++) {
			if (peca_arr[i].get_cor() == player_cor) {
				return peca_arr[i];
			}
		}
		return null;
	}
	
	protected boolean is_casa_comum() {
		if (tipo == 0) return true;
		return false;
	}
	protected boolean is_casa_inicial() {
		if (tipo == 1) return true;
		return false;
	}
	protected boolean is_casa_de_saida() {
		if (tipo == 2) return true;
		return false;
	}
	protected boolean is_abrigo() {
		if (tipo == 3) return true;
		return false;
	}
	protected boolean is_reta_final() {
		if (tipo == 4) return true;
		return false;
	}
	protected boolean is_casa_final() {
		if (tipo == 5) return true;
		return false;
	}
	protected boolean is_casa_de_entrada() {
		if (tipo == 6) return true;
		return false;
	}
}
