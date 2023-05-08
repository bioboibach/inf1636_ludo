package board;

import java.util.ArrayList;

protected class Path {    
	ArrayList<Tile> path = new ArrayList<Tile>();
	
	public Path() {
		// Quantidade de tiles
		int size = 52;
		int posCasasIni[] = new int[] {0, 13, 26, 39};
		int posCasasAbrigo[] = new int[] {9, 22, 35, 48};
		
		//Inicializa as casas do tabuleiro
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < posCasasIni.length; j++)
				if(i == posCasasIni[j])
					// Tile Entrada
					path.add(new EntranceTile());
				else if(i == posCasasAbrigo[j])
					// Tile Abrigo (safe)
					path.add(new SafeTile('s')); 
				else
					// Tile Comum
					path.add(new CommonTile('c'));  
		}
	}
	
	// retorna o numero de casas livres ate a proxima barreira 
	public int find_barrier(int ini, int fin){
		for (int i = fin - ini; i > 0; i--){
			if (!path[i].is_penetrable()){
				return i - 1;
			}
		}
		return fin - ini;
	}

	
	public int findEntrance(Team t, int qtMove, int ini, int fin){
		for(int i = 0; i < qtMove; i++){
			if(path[ini + i].isEntrance(t)){
				//Define a quantidade de movimentos a serem feitos dentro do EndPath
				return i;
				break;
			}
		}
		return -1;
	}
}
