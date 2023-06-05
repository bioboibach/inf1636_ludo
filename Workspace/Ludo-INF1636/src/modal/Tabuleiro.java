package modal;
import java.util.ArrayList;


class Tabuleiro {
	
	private static Tabuleiro instance;
	
	private ArrayList<Casa> path = new ArrayList<Casa>();
	private ArrayList<Casa> casas_iniciais = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_verde = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_amarelo = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_azul = new ArrayList<Casa>();
	
	private Tabuleiro() {
		start_casas();
	}
	
//	retorna true se a peca p ta dentro da path
	protected boolean is_in_path(Peca p) {
		if (path.indexOf(p.get_current_tile()) == -1) return false;
		return true;
	}
	
	protected boolean check_path(int index, int num_moves, Peca p){
		Casa c = p.get_current_tile();
		int count;
		for (count = 1; count < num_moves; count++) {
			c = path.get((index + count)%52);
			if (c.is_barreira()) return false;
		}
		if(c.casa_vaga(p)) {
			return true;
		}
		return false;
	}

	
	protected Casa get_casa_de_saida(int player_id) {
		if (player_id == 0) return get_path_index(0);
		else if (player_id == 1) return get_path_index(13);
		else if (player_id == 2) return get_path_index(26);
		else return get_path_index(39);	
	}
	
	protected Casa get_path_index(int i){
		return path.get(i);
	}
	protected Casa get_casas_iniciais_index(int i){
		return casas_iniciais.get(i);
	}
	protected Casa get_reta_final_vermelho_index(int i){
		return reta_final_vermelho.get(i);
	}
	protected Casa get_reta_final_verde_index(int i){
		return reta_final_verde.get(i);
	}
	protected Casa get_reta_final_amarelo_index(int i){
		return reta_final_amarelo.get(i);
	}
	protected Casa get_reta_final_azul_index(int i){
		return reta_final_azul.get(i);
	}

	protected int[] get_index_current_tile(Peca p) {
		int[] i = new int[2];
		if((i[0] = this.path.indexOf(p.get_current_tile())) != -1) {
			i[1] = 0;
			return i;
		}
		else if ((i[0] = this.casas_iniciais.indexOf(p.get_current_tile())) != -1) {
			i[1] = 1;
			return i;
		}
		else if ((i[0] = this.reta_final_vermelho.indexOf(p.get_current_tile())) != -1) {
			i[1] = 2;
			return i;
		}
		else if ((i[0] = this.reta_final_verde.indexOf(p.get_current_tile())) != -1) {
			i[1] = 3;
			return i;
		}
		else if ((i[0] = this.reta_final_amarelo.indexOf(p.get_current_tile())) != -1) {
			i[1] = 4;
			return i;
		}
		else {
			i[0] = this.reta_final_azul.indexOf(p.get_current_tile());
			i[1] = 5;
			return i;
		}
	}
	
	protected void start_casas() {
		int count;
		
//		Inicializa as casas_iniciais de cada jogador
		for (count = 0; count < 4; count++) {
			casas_iniciais.add(new Casa(1, count));
			casas_iniciais.get(count).add_peca(Jogo.getInstance().get_player(count).get_peca(0));
			Jogo.getInstance().get_player(count).get_peca(0).change_casa(casas_iniciais.get(count));
			casas_iniciais.get(count).add_peca(Jogo.getInstance().get_player(count).get_peca(1));
			Jogo.getInstance().get_player(count).get_peca(1).change_casa(casas_iniciais.get(count));
			casas_iniciais.get(count).add_peca(Jogo.getInstance().get_player(count).get_peca(2));
			Jogo.getInstance().get_player(count).get_peca(2).change_casa(casas_iniciais.get(count));
			casas_iniciais.get(count).add_peca(Jogo.getInstance().get_player(count).get_peca(3));
			Jogo.getInstance().get_player(count).get_peca(3).change_casa(casas_iniciais.get(count));
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

	protected static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
}
