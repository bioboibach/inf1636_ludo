package controller;

import modal.Modal_interface;
import java.io.File;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.io.FileNotFoundException;

class Load_game {
	private static Load_game instance;
	private FileReader outputStream = null;
	JFileChooser j = new JFileChooser();

	protected static Load_game getInstance() {
		if (instance == null) {
			instance = new Load_game();
		}
		return instance;
	}
	
	protected void load() throws IOException {
		Modal_interface m = Modal_interface.getInstance();
		
		int list_index, index;
		m.clear_tabuleiro();

		try {
			File save_file = new File("res/save_data/save_file.txt");
			Scanner scn = new Scanner(save_file);
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					index = scn.nextInt();
					System.out.print("index = " + index);
					list_index = scn.nextInt();
					System.out.println(" | list_index = " + list_index);
					m.set_positions(i, j, index, list_index);
				}
			}
			m.set_turn(scn.nextInt());
			scn.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
//	escreve o turno

// p00 p01 p02 p03 | p10 p11 p12 p13 | p20 p21 p22 p23 | p30 p31 p32 p33 | turn

//	p00 = [index, lista]
//	turn = ply id
