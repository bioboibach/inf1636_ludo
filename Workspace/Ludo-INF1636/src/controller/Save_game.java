package controller;

import modal.Modal_interface;
import java.io.*;

class Save_game {
	private static Save_game instance = null;
	
	protected void save() throws IOException{
		Modal_interface m = Modal_interface.getInstance();
		int[] aux = new int[2];
		FileWriter inputStream = new FileWriter("res/save_data/save_file.txt");
		
		try {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					aux = m.get_peca_indexes(i, j);
					inputStream.write(aux[0] + " ");
					inputStream.write(aux[1] + " ");
				}
			}
			int turn = m.get_player_turn();
			inputStream.write(turn + "");
			
		}
		catch (Exception e){
			e.getStackTrace();
		}
		finally {
			inputStream.close();
		}
	}
	
	protected static Save_game getInstance() {
		if (instance == null) {
			instance = new Save_game();
		}
		return instance;
	}
	
// p00 p01 p02 p03 | p10 p11 p12 p13 | p20 p21 p22 p23 | p30 p31 p32 p33 | turn
	
//	p00 = [index, lista]
//	turn = ply id
}
