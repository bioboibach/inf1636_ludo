package modal;

/*	Padrao Utilizado  
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
 * 	    -> -1 = neutro
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

//	Operacoes -------------------------------------------
//	assumo que pode mover
	protected void add_peca(Peca p) {
		num_pecas++;
		for (int i = 0; i < 4; i++) {
			if (peca_arr[i] == null) {
				peca_arr[i] = p;
				break;
			}
		}
	}
	protected void remove_peca(Peca p) {
		num_pecas--;
		for (int i = 0; i < 4; i++) {
			if (p.equals(peca_arr[i])) peca_arr[i] = null;
		}
	}
		
	
//	Verificacoes ---------------------------------------
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
	protected boolean is_casa_vaga(Peca p) {
//		se casa de final
		if (tipo == 5) return true;
		
//		se ta na reta final e vai para outra casa da reta final (nao tirou o numero exato para chegar no final)
		else if (p.get_current_casa().tipo == 4 && this.tipo == 4) return false;

//		se tem 2 pecas na casa
		else if(num_pecas == 2) return false;
		
		
//		TODO peca_arr[0]ta pegando so a primeira posicao, tem q ver se vai fazer update_casa pra sempre mover as pecas pra posicao 0 ou se so verifica qual index ta
//		se tem 1 peca na casa e a cor dessa peca for igual a da peca movimentada
		else if (num_pecas == 1) {
			Peca aux_p = peca_arr[0];
			if (aux_p == null) aux_p = peca_arr[1];
			
			if(p.get_cor() == aux_p.get_cor()) {
//				se casa de saída
				if (is_casa_de_saida()) return false;
				else return true;
			 }
		}
//		se tem 1 ou 0 pecas, se tem mais de 2 (casa inicial e final), 
		return true;
	}
	protected boolean is_barreira() {
		if (num_pecas < 2) return false;
		else if (peca_arr[0].get_cor() == peca_arr[1].get_cor()) return true;
		return false;
	}
	
	
//	Metodos get ----------------------------------------
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
			if (peca_arr[i] != null && peca_arr[i].get_cor() == player_cor) {
				return peca_arr[i];
			}
		}
		return null;
	}
	
}
