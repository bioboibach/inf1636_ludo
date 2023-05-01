package board;


public class Piece {
	// Cor que define o time a qual o Piece pertence
	TeamColors color;
	// Indice do array que indica a posicao no Path (principal)
	// Inicia-se com -1 pois ele comeca na base
	int pos = -1; 
	// Indice do array que indica a posicao no EndPath (reta final)
	// Inicia-se com -1 pois um Piece nao deve ser inicializado ja no EndPath
	int endPos = -1;
	
	public Piece (int team){
		color = TeamColors.values()[team];
	}
		
}
