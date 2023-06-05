package modal;
import java.util.*;
import controller.Observable;
import controller.Observer;

class Jogo implements Observable{
	
	private static Jogo instance;	

	private Tabuleiro t;
	private Dado d;
	private Player players[] = new Player[4];
	
	private Peca last_moved_piece = null;
	private int player_turn = 0;
	private int num_6_rolados = 0;
	private boolean capture_flag = false;
	
	private int current_piece;
	private int dado_res;
	
	List<Observer> lob = new ArrayList<Observer>();
	
	protected Jogo() {}
	
	protected void initialize_jogo() {
		start_players();
		start_board();
		start_dado();
		start_game();
	}
	
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
		
		if (check_end_game_condition() != -1) {
			end_game();
		}
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
	
	protected int check_end_game_condition() {
		if (t.get_reta_final_vermelho_index(5).get_num_pecas() == 4) return 0;
		else if	(t.get_reta_final_verde_index(5).get_num_pecas() == 4) return 1;
		else if	(t.get_reta_final_amarelo_index(5).get_num_pecas() == 4) return 2;
		else if	(t.get_reta_final_azul_index(5).get_num_pecas() == 4) return 3;
		return -1;
	}
	
	
//	TODO
//	fazer retornar uma peca clickada pelo mouse
	protected Peca pick_peca() {
		return players[player_turn].get_peca(0);
	}
	
	protected void end_game() {
//		TODO
//		finaliza o jogo
//		calcula 2o 3o e 4o lugar
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
	
	public void addObserver(Observer o) {
		lob.add(o);
	}
	public void removeObserver(Observer o) {
		lob.remove(o);
	}
	
	public Object get() {
		Object dados[] = new Object[5];
		dados[0] = new Integer (player_turn);
		dados[1] = new Integer (dado_res);
		dados[2] = new Integer (check_end_game_condition());
		dados[3] = new Integer (player_turn);
		dados[4] = new Integer (current_piece);
		dados[5] = new Integer (Tabuleiro.getInstance().get_index_current_tile(players[player_turn].get_peca(current_piece))[0]);
		dados[6] = new Integer (Tabuleiro.getInstance().get_index_current_tile(players[player_turn].get_peca(current_piece))[1]);
		return dados;
	}

	protected static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
}

