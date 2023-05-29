package modal;

import java.util.ArrayList;

class Jogo {
	
	private static Jogo instance;	

	private Tabuleiro t;
	private Dado d;
	private Player players[] = new Player[4];

	private ArrayList<Casa> path = new ArrayList<Casa>();
	private ArrayList<Casa> casas_iniciais = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_verde = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_amarelo = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_azul = new ArrayList<Casa>();
	
	private Peca last_moved_piece = null;
	private int player_turn = 0;
//	private ;
//	private ;
	
	protected Jogo() {
		start_players();
		start_board();
		start_casas();
		start_dado();
		
		start_game();
	}
	
	protected boolean check_path(int num_moves, Peca p){
		Casa c = p.get_current_tile();
		int index = path.indexOf(c);
		
		for (int count = 1; count < num_moves; count++) {
			c = path.get((index + count)%52);
			if (c.is_barreira()) return false;
		}
		return true;
	}
	
	protected void start_game() {
		
	}
	
	protected void start_players() {
		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i);
		}
	}
	protected void start_casas() {
		int count;
		
		for (count = 0; count < 4; count++) {
			casas_iniciais.add(new Casa(1, count));
			casas_iniciais.get(count).add_peca(players[count].get_peca(0));
			casas_iniciais.get(count).add_peca(players[count].get_peca(1));
			casas_iniciais.get(count).add_peca(players[count].get_peca(2));
			casas_iniciais.get(count).add_peca(players[count].get_peca(3));
		}
		
		for (count = 0; count < 4; count++) {
			reta_final_vermelho.add(new Casa(4, 0));
		}
		reta_final_vermelho.add(new Casa(5, 0));
		
		for (count = 0; count < 4; count++) {
			reta_final_verde.add(new Casa(4, 1));
		}
		reta_final_verde.add(new Casa(5, 1));
		
		for (count = 0; count < 4; count++) {
			reta_final_amarelo.add(new Casa(4, 2));
		}
		reta_final_amarelo.add(new Casa(5, 2));
		
		for (count = 0; count < 4; count++) {
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
			
			
//			inicializa casas de abrigo
			else if (count == 9 || count == 22 || count == 35 || count == 48)
			path.add(new Casa(3));
			
//			inicializa casas de entrada
			else if (count == 11) path.add(new Casa(6, 1));
			else if (count == 24) path.add(new Casa(6, 2));
			else if (count == 37) path.add(new Casa(6, 3));
			else if (count == 50) path.add(new Casa(6, 0));
			
//			inicializa todas as basicas
			else path.add(new Casa(0));
		}
		
	}
	protected void start_board() {
		t = Tabuleiro.getInstance();
	}
	protected void start_dado() {
		d = Dado.getInstance();
	}

//	(?)
	protected void captura(Casa c) {
		Peca p = c.get_p1();
		if (p == null) {
			p = c.get_p2();
		}
		c.remove_peca(p);
		casas_iniciais.get(p.get_cor()).add_peca(p);
	}
	
	protected Player get_player(int id){
		return players[id];
	}
	
	protected ArrayList<Casa> get_path(){
		return path;
	}
	
	protected boolean is_in_path(Peca p) {
		if (path.indexOf(p.get_current_tile()) == -1) return false;
		return true;
	}
	
	protected ArrayList<Casa> get_casas_iniciais(){
		return casas_iniciais;
	}
	
	protected ArrayList<Casa> get_reta_final_vermelho(){
		return reta_final_vermelho;
	}
	
	protected ArrayList<Casa> get_reta_final_verde(){
		return reta_final_verde;
	}
	
	protected ArrayList<Casa> get_reta_final_amarelo(){
		return reta_final_amarelo;
	}
	
	protected ArrayList<Casa> get_reta_final_azul(){
		return reta_final_azul;
	}

	
	protected static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
}

