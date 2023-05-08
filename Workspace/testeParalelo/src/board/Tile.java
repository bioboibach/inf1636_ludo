package board;


protected class Tile {
	//Define a cor dos Pieces na tile
	Team team;
	//Define a quantidade de Pieces na tile
	int qtPieces;
	
	public Tile() {
	}
	
	public void addPiece(Team t) {
		qtPieces++;
		team = TeamColors.values()[t];
	}
	
	public void removePiece() {
			
			try {
				qtPieces--;
				//Caso nao haja mais Pieces na Tile, tira-se a cor dele
				if (qtPieces == 0) {
				    team = null;
				}
			}
			catch (Exception E1){
				//Exceptions
				//E1: a Tile esta com zero Pieces, nao se pode remover mais
			}
			finally {}
		
	}
	
	
	
	//Retorna true se a Tile estiver vaga
	public boolean is_vacant() {
		return qtPieces <= 1;
	}
	
	
	//Retorna true se um Piece de outro time pode tomar a Tile
	public boolean is_penetrable() {
		//A Tile esta vazia ou tem Pieces do meu time

		//A Tile tem Pieces de outros time
			//A Tile e do tipo SafeTile

			//A Tile possui 1 Piece 
		return qtPieces <= 1;
	}
	
//	Funcoes que apenas retornam atributos do objeto

	public int pieces_in_tile() {
		return qtPieces;
	}

	public TeamColors get_color() {
		return team;
	}
	
	public boolean is_stacked() {
		return qtPieces >= 2;
	}
	
}
