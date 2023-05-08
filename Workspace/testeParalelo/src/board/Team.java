package board;

import java.util.ArrayList;

protected class Team {
	//Cor do time
	TeamColors color;
	
	//Posicao da EntranceTile do time
	int posEntrance;
	
	//Armazena a quantidade de Pieces na base
	//obs: um time comeca com todos os seu Pieces (4 no total)
	int qtPieces = 4;
	Piece arr_pieces[] = new Piece[4];
	
	//Quantidades de peoes na 'base'
	int qtPiecesBase = 4;
	//Quantidades de peoes que chegaram no final
	int qtPiecesEnd = 0;
	// //Posicoes das Pieces do time ex: [(posInit + 2, posInit + 3, posInit + 23, posInit + 40]
	// //Obs: a partir do (posInit + 52) vira endPath.
	// int posPieces[] = new int[];
	
	
	public Team(int team, int posInit) {
		//Define-se a cor da equipe baseado no argumento int
		color = TeamColors.values()[team];
		this.posEntrance= posInit;
		// //Pieces comecao na base (-1)
		// this.posPieces = [-1, -1, -1, -1];

		for(int i = 0; i < 4; i++)
			arr_pieces[i] = new Piece(team);
	
	}

	//Movimentacao de Pieces
	public void move(int piece_id){
		arr_pieces[piece_id].move(); 

	}
}
