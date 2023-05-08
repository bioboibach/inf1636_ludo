package board;


protected class Piece {
	// Cor que define o time a qual o Piece pertence
	TeamColors color;
	// Indice do array que indica a posicao no Path (principal)
	// Inicia-se com -1 pois ele comeca na base
	int posPath = -1; 
	// Indice do array que indica a posicao no EndPath (reta final)
	// Inicia-se com -1 pois um Piece nao deve ser inicializado ja no EndPath
	int posEndPath = -1;
	
	public Piece (int team){
		color = TeamColors.values()[team];
	}

	//Caso onde adiciona-se ao Path
	public updatePos(int qtMove)
		(posPath == 1)? posPath = 0;
		posPath += qtMove;
	
	//Caso quando adiciona-se um Piece ao EndPath
	public updatePos(int qtMoveEndPath, int methodSelector){
		posPath =  -2;
		posEndPath = qtMoveEndPath;
	}
	
	
}
