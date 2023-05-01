package board;

import java.util.ArrayList;

public class EndPath{
	ArrayList<Tile> path = new ArrayList<Tile>();
	
	public EndPath() {
		int size = 6;
		
		//Inicializa as Tiles do caminho final como Tiles Comuns
		for(int i = 0; i < size; i++)
			path.add(new Tile('c'));
	}
}
