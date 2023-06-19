package controller;

import modal.Modal_interface;

import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.*;

class Load_game {
	private static Load_game instance;
//	private FileReader outputStream = null;

	protected static Load_game getInstance() {
		if (instance == null) {
			instance = new Load_game();
		}
		return instance;
	}

	protected void load() throws IOException {
		Modal_interface m = Modal_interface.getInstance();
		JFileChooser f_chooser = new JFileChooser();

		int list_index, index;

		int b = f_chooser.showOpenDialog(null);
		if (b == JFileChooser.APPROVE_OPTION) {
			m.clear_tabuleiro();
			File save_file = f_chooser.getSelectedFile();
			String filepath = save_file.getPath();
			Scanner s = new Scanner(new BufferedReader(new FileReader(filepath)));

			try {

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						index = Integer.parseInt(s.next());
						list_index = Integer.parseInt(s.next());

						m.set_positions(i, j, index, list_index);
					}
				}
				m.set_turn(Integer.parseInt(s.next()));
				s.close();
				System.out.println("Jogo Carregado com sucesso");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}
//	escreve o turno

// p00 p01 p02 p03 | p10 p11 p12 p13 | p20 p21 p22 p23 | p30 p31 p32 p33 | turn

//	p00 = [index, lista]
//	turn = ply id