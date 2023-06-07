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

	protected Peca get_peca(int id) {
		return pecas[id];
	}
	protected int get_id(){
		return player_id;
	}
	
//	verifica se alguma peca pode se mover
	protected boolean can_move(int val_die) {
		for (int i = 0; i < 4; i++) {
			if(pecas[i].can_move(val_die)) return true;
		}
		return false;
	}
	
//	retorna null se n tem barreira
	protected Peca get_barrier() {
		int i, j;
		for(i = 0; i < 3; i++) {
			if (pecas[i].get_current_tile().is_casa_inicial() || pecas[i].get_current_tile().is_casa_final()) continue;
			for (j = i + 1; j < 4; j++) {
				if (pecas[j].get_current_tile().is_casa_inicial() || pecas[j].get_current_tile().is_casa_final()) continue;
				else if (pecas[i].get_current_tile().equals(pecas[j].get_current_tile())) return pecas[i];
			}
		}
		return null;
	}
	
}
