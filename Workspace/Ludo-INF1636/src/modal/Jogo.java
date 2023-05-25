package modal;

import java.util.ArrayList;

class Jogo {
	
	private static Jogo instance;	
	
	protected Tabuleiro t;
	protected Dado d;
	protected Player players[] = new Player[4];
	
	protected ArrayList<Casa> path = new ArrayList<Casa>();
	protected ArrayList<Casa> casas_iniciais = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_verde = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_amarelo = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_azul = new ArrayList<Casa>();
	
	protected Jogo() {
		start_players();
		start_board();
		start_casas();
		start_dado();
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
			reta_final_vermelho.add(new Casa(0, 4));
		}
		reta_final_vermelho.add(new Casa(0, 5));
		
		for (count = 0; count < 4; count++) {
			reta_final_verde.add(new Casa(1, 4));
		}
		reta_final_verde.add(new Casa(1, 5));
		
		for (count = 0; count < 4; count++) {
			reta_final_amarelo.add(new Casa(2, 4));
		}
		reta_final_amarelo.add(new Casa(2, 5));
		
		for (count = 0; count < 4; count++) {
			reta_final_azul.add(new Casa(3, 4));
		}
		reta_final_azul.add(new Casa(3, 5));
		
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
	}
	
	protected Player get_player(int id){
		return players[id];
	}
	
	
	
	public static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
}

