package controller;

import java.io.*;

import javax.swing.JFileChooser;
import modal.ModalAPI;

class Save_game {
	private static Save_game instance = null;
	Moment moment = Moment.getInstance();

	protected void save() throws IOException {
		ModalAPI m = ModalAPI.getInstance();
		int[] aux = new int[2];

		JFileChooser f_chooser = new JFileChooser();

		int b = f_chooser.showOpenDialog(null);
		if (b == JFileChooser.APPROVE_OPTION) {
			File save_file = f_chooser.getSelectedFile();
			FileWriter inputStream = new FileWriter(save_file.getPath());

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

			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				inputStream.close();
			}
		}
	}

	// Singleton ----------------------------------
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
