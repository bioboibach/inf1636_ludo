package controller;

import modal.Modal_interface;
import modal.Modal_interface.*;
import java.io.*;

class Load_game {
	private static Load_game instance;
	private FileReader outputStream = null;
	
	protected void load() {
		Modal_interface m = Modal_interface.getInstance();
		int list_index, index; 
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				m.set_positions(i, j, i, i);
			}
		}
		m.get_player_turn();
//		escreve o turno
	}
	protected static Load_game getInstance() {
		if (instance == null) {
			instance = new Load_game();
		}
		
		return instance;
	}
}
	
// p00 p01 p02 p03 | p10 p11 p12 p13 | p20 p21 p22 p23 | p30 p31 p32 p33 | turn
	
//	p00 = [index, lista]
//	turn = ply id


