package controller;

import java.io.*;
import java.util.Scanner;

import javax.swing.JFileChooser;
import modal.ModalAPI;

class Save_game {
	private static Save_game instance = null;
	Moment moment = Moment.getInstance();

	protected void save() throws IOException {
		ModalAPI m = ModalAPI.getInstance();
		int[] aux = new int[2];

		JFileChooser f_chooser = new JFileChooser();

		int list_index, index;

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

//	protected void save() throws IOException {
//	    JFileChooser f_chooser = new JFileChooser();
//
//	    int b = f_chooser.showSaveDialog(null);
//	    if (b == JFileChooser.APPROVE_OPTION) {
//	        File save_file = f_chooser.getSelectedFile();
//	        String filepath = save_file.getPath();
//
//	        saveDataToCSV(filepath);
//
//	        System.out.println("Jogo salvo com sucesso");
//	    }
//	}
//
//	private void saveDataToCSV(String filepath) {
//	    try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
//	        // Write the header row
//	        writer.println("casasIniciais,path,retaFinalVermelho,retaFinalVerde,retaFinalAmarelo,retaFinalAzul,podio,turno");
//
//	        // Write the data rows
//	        StringBuilder sb = new StringBuilder();
//	        sb.append(arrayToString(moment.get_casasIniciais())).append(",");
//	        sb.append(array2DToString(moment.getPath())).append(",");
//	        sb.append(arrayToString(moment.getRetaFinalVermelho())).append(",");
//	        sb.append(arrayToString(moment.getRetaFinalVerde())).append(",");
//	        sb.append(arrayToString(moment.getRetaFinalAmarelo())).append(",");
//	        sb.append(arrayToString(moment.getRetaFinalAzul())).append(",");
//	        sb.append(array2DToString(moment.getPodio())).append(",");
//	        sb.append(moment.getPlayer());
////	        sb.append(moment.getDiceVal());
//
//	        writer.println(sb.toString());
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
//
//	private String arrayToString(int[] array) {
//	    StringBuilder sb = new StringBuilder();
//	    for (int i = 0; i < array.length; i++) {
//	        sb.append(array[i]);
//	        if (i < array.length - 1) {
//	            sb.append("|");
//	        }
//	    }
//	    return sb.toString();
//	}
//
//	private String array2DToString(int[][] array) {
//	    StringBuilder sb = new StringBuilder();
//	    for (int i = 0; i < array.length; i++) {
//	        sb.append(arrayToString(array[i]));
//	        if (i < array.length - 1) {
//	            sb.append(";");
//	        }
//	    }
//	    return sb.toString();
//	}
//
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
