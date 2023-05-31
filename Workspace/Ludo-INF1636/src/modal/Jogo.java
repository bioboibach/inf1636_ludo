package modal;

import java.util.ArrayList;

class Jogo {
	
	private static Jogo instance;	

	private Tabuleiro t;
	private Dado d;
	private Player players[] = new Player[4];

//	TODO
//	acho q todas essas listas deviam ta dentro do board 
	
	private Peca last_moved_piece = null;
	private int player_turn = 0;

	
	protected Jogo() {
		start_players();
		start_board();
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
	
	
	
//	TODO
//	funcao encarregada de lidar com a captura
//	assumo que pode ocorrer captura
	protected void captura(Casa c) {
		Peca p = c.get_peca(0);
		if (p == null) {
			p = c.get_peca(1);
		}
		c.remove_peca(p);
		t.get_casas_iniciais_index(p.get_cor()).add_peca(p);
	}
	
	protected boolean check_end_game_condition() {
		if (t.get_reta_final_vermelho_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_verde_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_amarelo_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_azul_index(6).get_num_pecas() == 4)
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
	

//	retorna a Casa de index i dentro da lista
	
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

