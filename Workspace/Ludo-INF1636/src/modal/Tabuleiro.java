package modal;
import java.util.ArrayList;


class Tabuleiro {
	
	private static Tabuleiro instance;
	
	private ArrayList<Casa> path 				= new ArrayList<Casa>();
	private ArrayList<Casa> casasIniciais 		= new ArrayList<Casa>();
	private ArrayList<Casa> retaFinalVermelho 	= new ArrayList<Casa>();
	private ArrayList<Casa> retaFinalVerde 		= new ArrayList<Casa>();
	private ArrayList<Casa> retaFinalAmarelo 	= new ArrayList<Casa>();
	private ArrayList<Casa> retaFinalAzul 		= new ArrayList<Casa>();
	
// ____________________________________________________________________________________________________________________________
//
	
	private Tabuleiro() {
		start_casas();
	}

	//	Inicializacao --------------------------------------
	protected void start_casas() {
		int count;
		
//		Inicializa as casas_iniciais
		for (count = 0; count < 4; count++) {
			casasIniciais.add(new Casa(1, count));
		}
		
//		Inicializa as casas da reta final e a casa final de cada jogador
		for (count = 0; count < 5; count++) {
			retaFinalVermelho.add(new Casa(4, 0));
		}
		retaFinalVermelho.add(new Casa(5, 0));
		
		for (count = 0; count < 5; count++) {
			retaFinalVerde.add(new Casa(4, 1));
		}
		retaFinalVerde.add(new Casa(5, 1));
		
		for (count = 0; count < 5; count++) {
			retaFinalAmarelo.add(new Casa(4, 2));
		}
		retaFinalAmarelo.add(new Casa(5, 2));
		
		for (count = 0; count < 5; count++) {
			retaFinalAzul.add(new Casa(4, 3));
		}
		retaFinalAzul.add(new Casa(5, 3));
		
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
				c = p.get_currentCasa();
				c.remove_peca(p);
				p.change_casa(null);
			}
		}
	}
	
	//	Verificacoes ---------------------------------------
	//	retorna true se a peca p ta dentro da path
	protected boolean is_in_path(Peca p) {
		if (path.indexOf(p.get_currentCasa()) == -1) return false;
		return true;
	}
	protected boolean is_in_retaFinal(Peca p) {
		if (p.get_cor() == 0) {
			if (retaFinalVermelho.indexOf(p.get_currentCasa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 1) {
			if (retaFinalVerde.indexOf(p.get_currentCasa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 2) {
			if (retaFinalAmarelo.indexOf(p.get_currentCasa()) == -1) return false;
			else return true;
		}
		else if (p.get_cor() == 3) {
			if (retaFinalAzul.indexOf(p.get_currentCasa()) == -1) return false;
			else return true;
		}
		return true;
	}
	
	//	verifica o caminho e a casa de destino
	protected boolean check_path(int index, int num_moves, Peca p){
		Casa c; 
		if (this.check_for_barrier(index, num_moves, p)) {
			return false;
		}
		c = get_destination(index, num_moves, p, false);
		return c.is_casa_vaga(p);
	}
	
	//	return true se tem barreira no caminho ate casa de entrada da peca ou ate fim do movimento
	protected boolean check_for_barrier(int index, int num_moves, Peca p){
		Casa c = p.get_currentCasa();
		int count;
		for (count = 1; count <= num_moves; count++) {
			c = path.get((index + count)%52);
			if (c.is_barreira()) return true;
			else if (c.is_casa_de_entrada() && c.get_cor() == p.get_cor()) return false;
		}
		return false;
	}
	
	//	GET ----------------------------------------
	protected Casa get_destination(int index, int num_moves, Peca p, boolean is_in_reta_final){
		Casa c = p.get_currentCasa();
		int current_index = index;
		
		if(is_in_path(p)) {
			for (int i = 0; i <= num_moves; i++) {
				current_index = (index + i) % 52;
				c = get_pathIndex(current_index);

				if (c.is_casa_de_entrada() && c.get_cor() == p.get_cor() && i != num_moves) {
					c = get_retaFinalIndex(num_moves - i - 1, p.get_cor());
					break;
				}
			}
		}
		
		//	se nao esta na path
		else if (is_in_reta_final){
			if (index + num_moves > 5) return null;
			else c = get_retaFinalIndex(index + num_moves, p.get_cor());
		}
		else c = get_casaDeSaida(p.get_cor());
		return c;
	}
	
	protected Casa get_casaDeSaida(int player_id) {
		if (player_id == 0) return get_pathIndex(0);
		else if (player_id == 1) return get_pathIndex(13);
		else if (player_id == 2) return get_pathIndex(26);
		else return get_pathIndex(39);	
	}

//	=======================================
	protected ArrayList<Casa> get_casas_iniciais(){
		return casasIniciais;
	}	
	protected ArrayList<Casa> get_path(){
		return path;
	}
	protected ArrayList<Casa> get_rf_vermelho(){
		return retaFinalVermelho;
	}
	protected ArrayList<Casa> get_rf_verde(){
		return retaFinalVerde;
	}
	protected ArrayList<Casa> get_rf_amarelo(){
		return retaFinalAmarelo;
	}
	protected ArrayList<Casa> get_rf_azul(){
		return retaFinalAzul;
	}
//	=======================================
	
	
 	protected Casa get_pathIndex(int i){
		return path.get(i);
	}
	protected Casa get_casasIniciaisIndex(int i) {
		return casasIniciais.get(i);
	}
	protected Casa get_retaFinalIndex(int i, int player) {
		if (player == 0) return retaFinalVermelho.get(i);
		else if (player == 1) return retaFinalVerde.get(i);
		else if (player == 2) return retaFinalAmarelo.get(i);
		else if (player == 3) return retaFinalAzul.get(i);
		return null;
	}

	protected Casa get_casaFinal(int player_id) {
		if (player_id == 0) return retaFinalVermelho.get(5);
		else if (player_id == 1) return retaFinalVerde.get(5);
		else if (player_id == 2) return retaFinalAmarelo.get(5);
		else return retaFinalAzul.get(5);
	}
	
	protected int get_pathCurrentCasa(Peca p) {
		return path.indexOf(p.get_currentCasa());
	}
	protected int get_retaFinalCurrentCasa(Peca p) {
		if (p.get_cor() == 0) return retaFinalVermelho.indexOf(p.get_currentCasa());
		else if (p.get_cor() == 1) return retaFinalVerde.indexOf(p.get_currentCasa());
		else if (p.get_cor() == 2) return retaFinalAmarelo.indexOf(p.get_currentCasa());
		else return retaFinalAzul.indexOf(p.get_currentCasa());
	}

	protected int[] get_currentCasaIndex(Peca p) {
		int[] i = new int[2];
		if(get_pathCurrentCasa(p) != -1) {
			i[0] = get_pathCurrentCasa(p);
			i[1] = 0;
			return i;
		}
		else if ((i[0] = casasIniciais.indexOf(p.get_currentCasa())) != -1) {
			i[1] = 1;
			return i;
		}
		else if ((i[0] = retaFinalVermelho.indexOf(p.get_currentCasa())) != -1) {
			i[1] = 2;
			return i;
		}
		else if ((i[0] = retaFinalVerde.indexOf(p.get_currentCasa())) != -1) {
			i[1] = 3;
			return i;
		}
		else if ((i[0] = retaFinalAmarelo.indexOf(p.get_currentCasa())) != -1) {
			i[1] = 4;
			return i;
		}
		else {
			i[0] = retaFinalAzul.indexOf(p.get_currentCasa());
			i[1] = 5;
			return i;
		}
	}

	//	GET para o Observador -----------------------------------
	protected int[] getObs_casasIniciais(){
		//	obs_casas_iniciais[0] -> qtd de peoes nas casas iniciais do vermelho...
		int[] obs_casas_iniciais = new int[4];
		
		for (int i = 0; i < casasIniciais.size(); i++) {
    	    Casa casa = casasIniciais.get(i);
    	    
    	    obs_casas_iniciais[i] = casa.get_num_pecas();    	    
		}
		return obs_casas_iniciais;
	}	
	protected int[][] getObs_path(){
    	//	Cada casa de path eh representada por um int[2]
    	//	Obs: int[x][0] -> peao principal (fica embaixo no abrigo)
    	//	Obs: int[x][1] -> peao secundario (fica em cima no abrigo)
    	int[][] obs_path = new int[52][2];
    	
    	for (int i = 0; i < path.size(); i++) {
    	    Casa casa = path.get(i);
    	    
    	    switch(casa.get_num_pecas()) {
    	    case 0:
    	    	obs_path[i][0] = -1;
    	    	obs_path[i][1] = -1;
    	    	break;
    	    case 1:
    	    	if(casa.get_peca(0) != null) {
	    	    	obs_path[i][0] = casa.get_peca(0).get_cor();
    	    	}
    	    	else{
	    	    	obs_path[i][0] = casa.get_peca(1).get_cor();
    	    	}
    	    	obs_path[i][1] = -1;

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
		//	obs_rf_vermelho[0] -> qtd de peoes na primeira casa da reta final do vermelho
		int[] obs_rf_vermelho = new int[6];
		for (int i = 0; i < retaFinalVermelho.size(); i++) {
    	    Casa casa = retaFinalVermelho.get(i);
    	    obs_rf_vermelho[i] = casa.get_num_pecas();
		}
		return obs_rf_vermelho;
	}
	protected int[] getObs_rf_verde(){
		//	obs_rf_verde[0] -> qtd de peoes na primeira casa da reta final do verde
		int[] obs_rf_verde = new int[6];
		for (int i = 0; i < retaFinalVerde.size(); i++) {
    	    Casa casa = retaFinalVerde.get(i);
    	    obs_rf_verde[i] = casa.get_num_pecas();
		}
		return obs_rf_verde;
	}
	protected int[] getObs_rf_amarelo(){
		//	obs_rf_amarelo[0] -> qtd de peoes na primeira casa da reta final do amarelo
		int[] obs_rf_amarelo = new int[6];
		for (int i = 0; i < retaFinalAmarelo.size(); i++) {
    	    Casa casa = retaFinalAmarelo.get(i);
    	    
    	    obs_rf_amarelo[i] = casa.get_num_pecas();
		}
		return obs_rf_amarelo;
	}
	protected int[] getObs_rf_azul(){
		//	obs_rf_azul[0] -> qtd de peoes na primeira casa da reta final do azul
		int[] obs_rf_azul = new int[6];
		for (int i = 0; i < retaFinalAmarelo.size(); i++) {
    	    Casa casa = retaFinalAmarelo.get(i);
    	    
	    	obs_rf_azul[i] = casa.get_num_pecas();
		}
		return obs_rf_azul;
	}

	
	//	Singleton ------------------------------------------
	protected static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
}
