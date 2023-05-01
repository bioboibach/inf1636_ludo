package board;


public class Tile {
	
	//Tipo da tile: Entrada (Entrance - e), Abrigo (Safe - s) ou Comum (Common - c)
	char type;
	//Nas tiles do tipo Entrada, anexa-se a ela um caminho final (tiles coloridas do tabuleiro)  
	EndPath end = (type == 'e') ? new EndPath() : null;
	//Define a cor dos Pieces na tile
	TeamColors team;
	//Define a quantidade de Pieces na tile
	int qtPieces;
	
	public Tile(char type) {
		this.type = type;
	}
	
	
	// ==== Metodos Novos ====
	
	public void addPiece(int color) {
		
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
	
	
	// ==== Metodos Antigos ====
	
	//Metodos do Tile.java antigo atualizados
	//Obs:  'get_id', 'is_home', 'is_finish' nao foram aproveitados
	
	//Retorna true se a Tile estiver vaga
	public boolean is_vacant() {
		return qtPieces <= 1;
	}
	
	//Retorna true se um Piece de outro time pode tomar a Tile	
	//'is_passable' virou 'is_penetrable'
	public boolean is_penetrable() {
		return qtPieces <= 1;
	}
	
//	Funcoes que apenas retornam atributos do objeto

	public int pieces_in_tile() {
		return qtPieces;
	}
	//'is_basic' virou 'is_common' para seguir a terminologia sugerida pela regra
	public boolean is_common() {
		return type == 'c';
	}
	public boolean is_safe() {
		return type == 's';
	}
	//'is_entrance' virou 'is_entrance' para seguir a terminologia sugerida pela regra
	public boolean is_entrance() {
		return type == 'e';
	}
	public boolean is_colored() {
		return team != null;
	}
	
	//Metodos do Piece.java antigo atualizados
	//obs: stack_up, stack_down, get_id e get_stacked_ids nao pareceram ser necessarios
	public TeamColors get_color() {
		return team;
	}
	
	public boolean is_stacked() {
		return qtPieces >= 2;
	}
	
}
