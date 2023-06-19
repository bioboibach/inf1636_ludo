package modal;

class Player {

	private int player_id;
	private Peca pecas[] = new Peca[4];
	
	protected Player(int id) {
		player_id = id;
		for (int count = 0; count < 4; count++) {
			pecas[count] = new Peca(id);
		}
	}

	
//	Operacoes -------------------------------------------
	
	protected Peca pick_peca(int val_die) {
		Jogo j = Jogo.getInstance();
		Peca p = j.get_player(j.get_turn()).get_peca(0);
		
		int[] possible_pieces = new int[4];
		possible_pieces = get_movable_pieces(val_die);
		
		for (int i = 0; i < 4; i++) if (possible_pieces[i] == 1) return pecas[i];
		
		return p;
	}

	
//	Verificacoes ---------------------------------------
	
//	verifica se alguma peca pode se mover
	protected boolean can_move(int val_die) {
		for (int i = 0; i < 4; i++) {
			if(pecas[i].can_move(val_die)) return true;
		}
		return false;
	}
	
	
//	Metodos get ----------------------------------------
	
//	retorna lista de quais pecas podem se mover
	protected int[] get_movable_pieces(int val_die) {
		int[] possible_moves = new int[4];
		for (int i = 0; i < 4; i++) {
			if(pecas[i].can_move(val_die)) possible_moves[i] = 1;
			else possible_moves[i] = 0;
		}
		return possible_moves;
	}
	
//	retorna null se nao tem barreira
	protected Peca get_barrier() {
		int i, j;
		for(i = 0; i < 3; i++) {
			if (pecas[i].get_current_casa().is_casa_inicial() 	|| 
				pecas[i].get_current_casa().is_casa_final() 	|| 
				pecas[i].get_current_casa().is_casa_de_saida()
				)continue;
			for (j = i + 1; j < 4; j++) {
				if (pecas[j].get_current_casa().is_casa_inicial() 	||
					pecas[j].get_current_casa().is_casa_final() 	||
					pecas[j].get_current_casa().is_casa_de_saida()
					)continue;
				else if (pecas[i].get_current_casa().equals(pecas[j].get_current_casa()))
					return pecas[i];
			}
		}
		return null;
	}
	
	protected Peca get_peca(int id) {
		return pecas[id];
	}
	protected int get_id(){
		return player_id;
	}
	
}
