package board;

import java.util.ArrayList;

protected class EndPath{
	ArrayList<EndTile> path = new ArrayList<EndTile>();
	
	public EndPath(TeamColors team) {
		int size = 6;
		
		//Inicializa as Tiles do caminho final como Tiles Comuns
		for(int i = 0; i < size; i++)
			path.add(new EndTile(team));
	}


	public getTile(int t)
		return path[t];
}
