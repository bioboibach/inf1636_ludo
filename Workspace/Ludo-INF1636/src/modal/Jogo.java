package modal;

import java.util.ArrayList;

class Jogo {
	
	private static Jogo instance;	

	private Tabuleiro t;
	private Dado d;
	private Player players[] = new Player[4];

//	TODO
//	acho q todas essas listas deviam ta dentro do board 
	private ArrayList<Casa> path = new ArrayList<Casa>();
	private ArrayList<Casa> casas_iniciais = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_vermelho = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_verde = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_amarelo = new ArrayList<Casa>();
	private ArrayList<Casa> reta_final_azul = new ArrayList<Casa>();
	
	private Peca last_moved_piece = null;
	private int player_turn = 0;

	
	protected Jogo() {
		start_players();
		start_board();
		start_casas();
		start_dado();
	}
	
//	TODO
//	funcao q faz o turno acontecer
	protected void turn() {
	/* 	
	 * 	check_end_game_condition()
	 * 
	 *  rola o dado
	 * 
	 * 	if valor do dado = 5
	 * 		if tem pecas na casa inicial
	 * 			if pode mover peca pra casa de saida
	 * 				p.move_to_casa_de_saida()
	 * 
	 *	else if valor do dado = 6
	 * 		num_de_6++ (update numero de vezes q tirou 6)
	 * 		if num_de_6 > 2
	 * 			if last_moved_piece movimentada n ta na reta final ou chegada
	 *  			last_moved_piece.move_to_base()
	 *  		num_de_6 = 0
	 *  		end_turn()
	 *  
	 *  	else
	 * 			if pode mover alguma peca de barreira
	 * 				pega um p da barreira
	 * 				p.move()
	 * 
	 *	 		else if pode mover so uma peca
	 * 				p.move()
	 * 
	 * 			else if pode escolher qual peca andar
	 * 				escolhe p
	 * 				p.move()
	 * 			
	 *	else
	 *		if so tem uma peca q pode ser movida
	 * 			p.move()
	 * 
	 *  	else if pode escolher
	 *  		escolhe p
	 * 			p.move()
	 *  			
	 *  verifica se capturou alguma peca
	 *  if capturou
	 *  	if so tem uma peca q pode ser movida
	 * 			p.move()
	 * 
	 *  	else if pode escolher
	 * 			escolhe p
	 * 			p.move()
	 * 
	 *  end_turn()
	 *  	
	 */			
		
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
	
//	TODO
//	funcao encarregada de lidar com a captura
//	assumo que pode ocorrer captura
	protected void captura(Casa c) {
		Peca p = c.get_p1();
		if (p == null) {
			p = c.get_p2();
		}
		c.remove_peca(p);
		casas_iniciais.get(p.get_cor()).add_peca(p);
	}
	
	protected boolean check_end_game_condition() {
		if (reta_final_vermelho.get(6).get_num_pecas() == 4 ||
			reta_final_verde.get(6).get_num_pecas() == 4 ||
			reta_final_amarelo.get(6).get_num_pecas() == 4 ||
			reta_final_azul.get(6).get_num_pecas() == 4)
			return true;
		return false;
	}
	
	protected void end_game() {
//		TODO
//		finaliza o jogo
	}
	
	protected void end_turn() {
		player_turn = (player_turn + 1) % 4;
	}
	
	protected void update_last_moved_piece(Peca p) {
		last_moved_piece = p;
	}
		
	protected Peca get_last_moved_piece() {
		return last_moved_piece;
	}
	protected Player get_player(int id){
		return players[id];
	}
	
//	retorna true se a peca p ta dentro da path
	protected boolean is_in_path(Peca p) {
		if (path.indexOf(p.get_current_tile()) == -1) return false;
		return true;
	}
	
//	retorna a Casa de index i dentro da lista
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

//	inicia o jogo, ja movendo a primeira peca vermelha pra casa de saida
	protected void start_game() {
		Peca p = players[0].get_peca(0);
		p.move_to_casa_de_saida();
		update_last_moved_piece(p);
		end_turn();
	}
	protected void start_players() {
		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i);
		}
	}
	protected void start_casas() {
		int count;
		
//		Inicializa as casas_iniciais de cada jogador
		for (count = 0; count < 4; count++) {
			casas_iniciais.add(new Casa(1, count));
			casas_iniciais.get(count).add_peca(players[count].get_peca(0));
			casas_iniciais.get(count).add_peca(players[count].get_peca(1));
			casas_iniciais.get(count).add_peca(players[count].get_peca(2));
			casas_iniciais.get(count).add_peca(players[count].get_peca(3));
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

	protected static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
}

