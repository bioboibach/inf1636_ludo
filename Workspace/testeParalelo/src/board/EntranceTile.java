package board;

protected class EntranceTile extends Tile{
	EndPath endpath;
		
	public EntranceTile(char type, TeamColors team) {
		super(type);
		this.team = team;
		path = new EndPath(team);
	}

	//Sobreescreve o metodo que herdou de Tile
	public void addPiece(int qtMoveEndPath) {
		
		endpath.getTile(qtMoveEndPath).addPiece();
		
		try {
			qtPieces++;
			team = TeamColors.values()[color];
		}
		catch (Exception E1){
			//Exceptions
			//E1: a Tile esta com a capacidade maxima de Pieces, nao se pode adicionar mais
			//E2: a Tile ja tem um Piece de outra cor
		}
		finally {}
		
	}
}
