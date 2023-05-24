package modal;

import java.util.ArrayList;

class Jogo {
	protected Tabuleiro t;
	protected Player players[] = new Player[4];
	
	protected ArrayList<Casa> path = new ArrayList<Casa>();
	protected ArrayList<Casa> casas_iniciais = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_verde = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_amarelo = new ArrayList<Casa>();
	protected ArrayList<Casa> reta_final_azul = new ArrayList<Casa>();
	
	protected Jogo() {}
	
	
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
		
		for (count = 0; count < 55; count++) {
			
			if (count == 0 || count == 13 || count == 26 || count == 39)
			path.add(new Casa(2));
			
		}
		
	}
	
	protected void start_board() {
//		t = Tabuleiro.getInstance();
		
		
	}
	
	protected Player get_player(int id){
		return players[id];
	}
	
}

