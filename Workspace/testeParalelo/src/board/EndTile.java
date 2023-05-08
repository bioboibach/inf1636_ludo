package board;

protected class EndTile extends Tile{
	
	public EndTile(TeamColors team) {
		super('c');
		this.team = team;		
	}

	public void addPiece() {
		
		try {
			qtPieces++;
		}
		catch (Exception E1){
			//Exceptions
			//E1: a Tile esta com a capacidade maxima de Pieces, nao se pode adicionar mais
		}
		finally {}
		
	}

}
