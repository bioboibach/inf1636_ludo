package board;

import java.util.ArrayList;

public class Path {    
	ArrayList<Tile> path = new ArrayList<Tile>();
	
	public Path() {
		// Quantidade de tiles
		int size = 52;
		int posCasasIni[] = new int[] {0, 13, 26, 39};
		int posCasasAbrigo[] = new int[] {5, 18, 31, 44};		
		
		//Inicializa as casas do tabuleiro
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < posCasasIni.length; j++)
				if(i == posCasasIni[j])
					// Tile Entrada
					path.add(new Tile('e'));
				else if(i == posCasasAbrigo[j])
					// Tile Abrigo (safe)
					path.add(new Tile('s')); 
				else
					// Tile Comum
					path.add(new Tile('c'));  
		}
		
	}
	
}
