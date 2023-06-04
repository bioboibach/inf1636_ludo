package modal;

class Jogo {
	
	private static Jogo instance;	

	private Tabuleiro t;
	private Dado d;
	private Player players[] = new Player[4];
	
	private Peca last_moved_piece = null;
	private int player_turn = 0;
	private int num_6_rolados = 0;
	private boolean capture_flag = false;
	
	protected Jogo() {}
	
	protected void initialize_jogo() {
		start_players();
		start_board();
		start_dado();
		start_game();
	}
	
//	TODO
//	funcao q faz o turno acontecer
	protected void turn() {
		
		int val = d.roll();
		Player ply = players[player_turn];
		Peca p;
		Casa c;
		
		switch(val) {
		
		case 5:
			c = t.get_casas_iniciais_index(ply.get_id());
			
//			se tem peca na casa inicial
			if (c.get_num_pecas() != 0) {
				p = c.get_primeira_peca_player(ply);
//				se casa (de saida) vaga
				if (t.get_casa_de_saida(ply.get_id()).casa_vaga(p)) {
					p.move_to_casa_de_saida();
					update_last_moved_piece(p);
				}
			}
//			se pode mover alguma coisa
			else if (ply.can_move(val)) {
				p = pick_peca();
				p.move(val);
				update_last_moved_piece(p);
			}
			break;
			
		case 6:
			num_6_rolados++;
			if (num_6_rolados == 3) {
				c = last_moved_piece.get_current_tile();
				if(c.get_tipo() != 5 && c.get_tipo() != 4) {
					last_moved_piece.move_to_base();
				}
				end_turn();
				return;
			}
			
//			se player tem barreira
			else if (ply.get_barrier() != null){
				p = ply.get_barrier();
				p.move(val);
				update_last_moved_piece(p);
			}
			
			else if (ply.can_move(val)){
				p = pick_peca();
				p.move(val);
				update_last_moved_piece(p);
			}
			turn();
			break;

		default:
			if (ply.can_move(val)) {
				p = pick_peca();
				p.move(val);
				update_last_moved_piece(p);
			}
		}
		
		while (last_moved_piece.get_current_tile().is_casa_final() || capture_flag == true) {
			if(ply.can_move(6)) {
				p = pick_peca();
				p.move(val);
				update_last_moved_piece(p);
			}
			else break;
		}
		
		check_end_game_condition();
		end_turn();
		return;		
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
	
	protected void check_end_game_condition() {
		if (t.get_reta_final_vermelho_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_verde_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_amarelo_index(6).get_num_pecas() == 4 ||
			t.get_reta_final_azul_index(6).get_num_pecas() == 4)
			end_game();
	}
	
	
//	TODO
//	fazer retornar uma peca clickada pelo mouse
	protected Peca pick_peca() {
		return players[player_turn].get_peca(0);
	}
	
	protected void end_game() {
//		TODO
//		finaliza o jogo
	}
	
	protected void end_turn() {
		player_turn = (player_turn + 1) % 4;
		num_6_rolados = 0;
		capture_flag = false;
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

