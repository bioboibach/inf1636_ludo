package modal;
import java.util.ArrayList;


class Tabuleiro {
	
	private static Tabuleiro instance;
	
	private ArrayList<Casa> path 				= new ArrayList<Casa>();
	private ArrayList<Casa> casas_iniciais 		= new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_verde 	= new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_amarelo 	= new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_azul 	= new ArrayList<Casa>();
	
	protected static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
	private Tabuleiro() {
		start_casas();
	}

//	Inicializacao --------------------------------------
	protected void start_casas() {
		int count;
		
//		Inicializa as casas_iniciais
		for (count = 0; count < 4; count++) {
			casas_iniciais.add(new Casa(1, count));
		}
		
//		Inicializa as casas da reta final e a casa final de cada jogador
		for (count = 0; count < 5; count++) {
			reta_final_vermelho.add(new Casa(4, 0));
		}
		reta_final_vermelho.add(new Casa(5, 0));
		
		for (count = 0; count < 5; count++) {
			reta_final_verde.add(new Casa(4, 1));
		}
		reta_final_verde.add(new Casa(5, 1));
		
		for (count = 0; count < 5; count++) {
			reta_final_amarelo.add(new Casa(4, 2));
		}
		reta_final_amarelo.add(new Casa(5, 2));
		
		for (count = 0; count < 5; count++) {
			reta_final_azul.add(new Casa(4, 3));
		}
		reta_final_azul.add(new Casa(5, 3));
		
//		path
		for (count = 0; count <= 51; count++) {

//			inicializa casas de saida
			if (count == 0) path.add(new Casa(2, 0)); 
			else if (count == 13) path.add(new Casa(2, 1));
			else if (count == 26) path.add(new Casa(2, 2));
			else if (count == 39) path.add(new Casa(2, 3));			
			
//			inicializa casas de entrada
			else if (count == 11) path.add(new Casa(6, 1));
			else if (count == 24) path.add(new Casa(6, 2));
			else if (count == 37) path.add(new Casa(6, 3));
			else if (count == 50) path.add(new Casa(6, 0));
			
//			inicializa casas de abrigo
			else if (count == 9 || count == 22 || count == 35 || count == 48)
				path.add(new Casa(3));
			
//			inicializa todas as basicas
			else path.add(new Casa(0));
		}
	}
	protected void clear_tabuleiro() {
		int i, j;
		
		Peca p;
		Casa c;
		Jogo jg = Jogo.getInstance();
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				p = jg.get_player(i).get_peca(j);
				c = p.get_current_casa();
				c.remove_peca(p);
				p.change_casa(null);
			}
		}
	}
	
//	Verificacoes ---------------------------------------
//	retorna true se a peca p ta dentro da path
	protected boolean is_in_path(Peca p) {
		if (path.indexOf(p.get_current_casa()) == -1) return false;
		return true;
	}
	protected boolean is_in_reta_final(Peca p) {
		if (p.get_cor() == 0) {
			if (reta_final_vermelho.indexOf(p.get_current_casa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 1) {
			if (reta_final_verde.indexOf(p.get_current_casa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 2) {
			if (reta_final_amarelo.indexOf(p.get_current_casa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 3) {
			if (reta_final_azul.indexOf(p.get_current_casa()) == -1) return false;
			else return true;
		}
		return true;
	}
	
//	verifica o caminho e a casa de destino
	protected boolean check_path(int index, int num_moves, Peca p){
		Casa c; 
		if (this.check_for_barrier(index, num_moves, p)) return false;
		c = get_destination(index, num_moves, p, false);
		return c.is_casa_vaga(p);
	}
	
//	return true se tem barreira no caminho ate casa de entrada da peca ou ate fim do movimento
	protected boolean check_for_barrier(int index, int num_moves, Peca p){
		Casa c = p.get_current_casa();
		int count;
		for (count = 1; count < num_moves; count++) {
			c = path.get((index + count)%52);
			if (c.is_barreira()) return true;
			else if (c.is_casa_de_entrada() && c.get_cor() == p.get_cor()) return false;
		}
		return false;
	}
	
//	Metodos get ----------------------------------------
	protected Casa get_destination(int index, int num_moves, Peca p, boolean is_in_reta_final){
		Casa c = p.get_current_casa();
		int current_index = index;
		
		if(is_in_path(p)) {
			for (int i = 0; i <= num_moves; i++) {
				current_index = (index + i) % 52;
				c = get_path_index(current_index);

				if (c.is_casa_de_entrada() && c.get_cor() == p.get_cor() && i != num_moves) {
					c = get_reta_final_index(num_moves - i - 1, p.get_cor());
					break;
				}
			}
		}
		
//		se n ta na path
		else if (is_in_reta_final){
			if (index + num_moves > 5) return null;
			else c = get_reta_final_index(index + num_moves, p.get_cor());
		}
		else c = get_casa_de_saida(p.get_cor());
		return c;
	}
	
	protected Casa get_casa_de_saida(int player_id) {
		if (player_id == 0) return get_path_index(0);
		else if (player_id == 1) return get_path_index(13);
		else if (player_id == 2) return get_path_index(26);
		else return get_path_index(39);	
	}

//	========================= TODO remove
	protected ArrayList<Casa> get_casas_iniciais(){
		return casas_iniciais;
	}	
	protected ArrayList<Casa> get_path(){
		return path;
	}
	protected ArrayList<Casa> get_rf_vermelho(){
		return reta_final_vermelho;
	}
	protected ArrayList<Casa> get_rf_verde(){
		return reta_final_verde;
	}
	protected ArrayList<Casa> get_rf_amarelo(){
		return reta_final_amarelo;
	}
	protected ArrayList<Casa> get_rf_azul(){
		return reta_final_azul;
	}
//	=======================================
	
	
 	protected Casa get_path_index(int i){
		return path.get(i);
	}
	protected Casa get_casas_iniciais_index(int i) {
		return casas_iniciais.get(i);
	}
	protected Casa get_reta_final_index(int i, int cor) {
		if (cor == 0) return reta_final_vermelho.get(i);
		else if (cor == 1) return reta_final_verde.get(i);
		else if (cor == 2) return reta_final_amarelo.get(i);
		else /*if (cor == 3)*/ return reta_final_azul.get(i);
	}

	protected Casa get_casa_final(int player_id) {
		if (player_id == 0) return reta_final_vermelho.get(5);
		else if (player_id == 1) return reta_final_verde.get(5);
		else if (player_id == 2) return reta_final_amarelo.get(5);
		else return reta_final_azul.get(5);
	}
	
	protected int get_path_current_casa(Peca p) {
		return path.indexOf(p.get_current_casa());
	}
	protected int get_reta_final_current_casa(Peca p) {
		if (p.get_cor() == 0) return reta_final_vermelho.indexOf(p.get_current_casa());
		else if (p.get_cor() == 1) return reta_final_verde.indexOf(p.get_current_casa());
		else if (p.get_cor() == 2) return reta_final_amarelo.indexOf(p.get_current_casa());
		else return reta_final_azul.indexOf(p.get_current_casa());
	}

	protected int[] get_index_current_casa(Peca p) {
		int[] i = new int[2];
		if((i[0] = get_path_current_casa(p)) != -1) {
			i[1] = 0;
			return i;
		}
		else if ((i[0] = casas_iniciais.indexOf(p.get_current_casa())) != -1) {
			i[1] = 1;
			return i;
		}
		else if ((i[0] = reta_final_vermelho.indexOf(p.get_current_casa())) != -1) {
			i[1] = 2;
			return i;
		}
		else if ((i[0] = reta_final_verde.indexOf(p.get_current_casa())) != -1) {
			i[1] = 3;
			return i;
		}
		else if ((i[0] = reta_final_amarelo.indexOf(p.get_current_casa())) != -1) {
			i[1] = 4;
			return i;
		}
		else {
			i[0] = reta_final_azul.indexOf(p.get_current_casa());
			i[1] = 5;
			return i;
		}
	}

//	Metodos get para o Observador -----------------------------------
	protected int[] getObs_casas_iniciais(){
//		obs_casas_iniciais[0] -> qtd de peoes nas casas iniciais do vermelho...
		int[] obs_casas_iniciais = new int[4];
		
		for (int i = 0; i < casas_iniciais.size(); i++) {
    	    Casa casa = casas_iniciais.get(i);
    	    
    	    obs_casas_iniciais[i] = casa.get_num_pecas();    	    
		}
		return obs_casas_iniciais;
	}	
	protected int[][] getObs_path(){
//    	Cada casa de path eh representada por um int[2]
//    	Obs: int[x][0] -> peao principal (fica embaixo no abrigo)
//    	Obs: int[x][1] -> peao secundario (fica em cima no abrigo)
    	int[][] obs_path = new int[52][2];
    	
    	for (int i = 0; i < path.size(); i++) {
    	    Casa casa = path.get(i);
    	    
    	    switch(casa.get_num_pecas()) {
    	    case 0:
    	    	obs_path[i][0] = -1;
    	    	obs_path[i][1] = -1;
    	    	break;
    	    case 1:
    	    	obs_path[i][0] = casa.get_peca(0).get_cor();
    	    	break;
    	    case 2:
    	    	obs_path[i][0] = casa.get_peca(0).get_cor();
    	    	obs_path[i][1] = casa.get_peca(1).get_cor();
    	    	break;
    	    }
    	}
    	
    	return obs_path;

	}
	
	protected int[] getObs_rf_vermelho(){
//		obs_rf_vermelho[0] -> qtd de peoes na primeira casa da reta final do vermelho
		int[] obs_rf_vermelho = new int[6];
		for (int i = 0; i < reta_final_vermelho.size(); i++) {
    	    Casa casa = reta_final_vermelho.get(i);
    	    obs_rf_vermelho[i] = casa.get_num_pecas();
		}
		return obs_rf_vermelho;
	}
	protected int[] getObs_rf_verde(){
//		obs_rf_verde[0] -> qtd de peoes na primeira casa da reta final do verde
		int[] obs_rf_verde = new int[6];
		for (int i = 0; i < reta_final_verde.size(); i++) {
    	    Casa casa = reta_final_verde.get(i);
    	    obs_rf_verde[i] = casa.get_num_pecas();
		}
		return obs_rf_verde;
	}
	protected int[] getObs_rf_amarelo(){
//		obs_rf_amarelo[0] -> qtd de peoes na primeira casa da reta final do amarelo
		int[] obs_rf_amarelo = new int[6];
		for (int i = 0; i < reta_final_amarelo.size(); i++) {
    	    Casa casa = reta_final_amarelo.get(i);
    	    obs_rf_amarelo[i] = casa.get_num_pecas();
		}
		return obs_rf_amarelo;
	}
	protected int[] getObs_rf_azul(){
//		obs_rf_azul[0] -> qtd de peoes na primeira casa da reta final do azul
		int[] obs_rf_azul = new int[6];
		for (int i = 0; i < reta_final_amarelo.size(); i++) {
    	    Casa casa = reta_final_amarelo.get(i);
    	    obs_rf_azul[i] = casa.get_num_pecas();
		}
		return obs_rf_azul;
	}


}
