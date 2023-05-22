package modal;

/* tile matriz_de_tile[15x15] */
class Tabuleiro {
	
	Casa tile_matrix[][] = new Casa[15][15];
	
	private static Tabuleiro instance;

	private Tabuleiro() {
		
		int i, j;
		for (i = 0; i < 15; i++) {
			for (j = 0; j < 15; j++) {
				tile_matrix[i][j] = new Casa(i, j); // passa as coordenadas da casa pra decidir oq vai ser
			}
		}
	}

	public static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
	
}
