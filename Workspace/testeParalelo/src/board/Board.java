package board;

protected class Board {
	Path path;
	int qtSix = 0;
	protected Board() {
		//Inicializa o caminho 
		//obs: total de casas = 52
		Path path = new Path();
		
		//Inicializa os times
		//Obs: posicao das EntraceTile hara-coded
		Team t1 = new Team(1, 0);
		Team t2 = new Team(2, 13);
		Team t3 = new Team(3, 26);
		Team t4 = new Team(4, 39);

		Dado d = new Dado();
		
	}
	
	//Movimentacao de Pieces
	//t -> time, p -> indice do Piece na array de Pieces do time
	protected move(Team t, int p){
		//Rola o dado
		int qtMove = d.roll();
		
		//Posicao inicial do Piece
		int ini = t.arr_pieces[p].pos;
		//Posicao final teorica do Piece apos a movimentacao 
		int fin = (ini + qtMove) % 52;

		// verifica se pode mover pra la
		qtMove = path.find_barrier(ini, fin);
		fin = ini + qtMove;
		
		//Verifica se a casa inicial do endPath do time esta no caminho.
		//Caso esteja, salva-se a distancia ate ela
		int distToEntrance = path.findEntrance(t, qtMove, ini, fin);

		//Remove o Piece da posicao original
		path[t.arr_pieces[p].pos].removePiece();

		//Caso nao haja o EntranceTile do time no caminho, adiciona-se um Piece no Path
		if(distToEntrance == -1){
			//Adiciona ao Path 
			path[t.arr_pieces[p].pos + qtMove].addPiece(t);
			//Atualiza posicao no Piece
			t.getPiece(p).updatePos(qtMove);
		}
		//Caso haja, tenta-se adicionar um Piece no EndPath
		else{
			int qtMoveEndPath = qtMove - distEntrance;
			//Adiciona ao Path 
			path[t.arr_pieces[p].pos + distEntrance].addPiece(qtMoveEndPath);

			//Atualiza a posicao no Piece
			t.getPiece(p).updatePos(qtMoveEndPath, 1);
		}

		
		//Verificando a regra do 6
		if(qtMove == 6){
			this.qtSix += 1;
			if(this.qtSix == 3)
				t.getPiece(p).updatePos(-1);
			move(t, p);
		}
	}
}
