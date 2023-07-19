package controller;

import modal.ModalAPI;
import java.io.*;
import javax.swing.JFileChooser;
import java.util.Scanner;
import view.ViewAPI;

class Load_game {
	private static Load_game instance;
//	private FileReader outputStream = null;
	Moment moment = Moment.getInstance();

	protected void load() throws IOException {
		ModalAPI modalAPI = ModalAPI.getInstance();
		JFileChooser f_chooser = new JFileChooser();

		int list_index, index;

		int b = f_chooser.showOpenDialog(null);
		if (b == JFileChooser.APPROVE_OPTION) {
			modalAPI.clear_tabuleiro();
			File save_file = f_chooser.getSelectedFile();
			String filepath = save_file.getPath();
			Scanner s = new Scanner(new BufferedReader(new FileReader(filepath)));

				for (int i = 0; i < 4; i++) {
					
					for (int j = 0; j < 4; j++) {
						
						index = Integer.parseInt(s.next());
						
						list_index = Integer.parseInt(s.next());
						
						modalAPI.set_positions(i, j, index, list_index);
						
					}
				}
				modalAPI.set_turn(Integer.parseInt(s.next()));
				modalAPI.update_moment();
				s.close();
				System.out.println("Jogo Carregado com sucesso");
		}
	}
	
//	protected void load() throws IOException{
//	    JFileChooser f_chooser = new JFileChooser();
//
//	    int b = f_chooser.showSaveDialog(null);
//	    if (b == JFileChooser.APPROVE_OPTION) {
//	        File save_file = f_chooser.getSelectedFile();
//	        String filepath = save_file.getPath();
//
//	        readDataFromCSV(filepath);
//
//	        System.out.println("Jogo carregado com sucesso");
//	    }
//	}
//	
//	private void readDataFromCSV(String filepath) {
//	    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
//	        // Read the header row
//	        String header = reader.readLine();
//	        @SuppressWarnings("unused")
//			String[] headers = header.split(",");
//
//	        // Read the data row
//	        String data = reader.readLine();
//	        String[] rowData = data.split(",");
//
//	        // Extract the values from the data row
//	        int[] 	casasIniciais = stringToArray(rowData[0]);
//	        int[][] path = stringTo2DArray(rowData[1]);
//	        int[]	retaFinalVermelho = stringToArray(rowData[2]);
//	        int[]	retaFinalVerde = stringToArray(rowData[3]);
//	        int[] 	retaFinalAmarelo = stringToArray(rowData[4]);
//	        int[]	retaFinalAzul = stringToArray(rowData[5]);
//	        int[][]	podio = stringTo2DArray(rowData[6]);
//	        int		turno = Integer.parseInt(rowData[7]);
////	        int		diceVal = Integer.parseInt(rowData[8]);
//
////	        moment.print();
//	        
//	        
//	        moment.setAll(casasIniciais, path, retaFinalVermelho, retaFinalVerde, retaFinalAmarelo, retaFinalAzul, podio, turno, 5);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	    ViewAPI.getInstance().updateBoardInfo();
//	}
//
//	private int[] stringToArray(String str) {
//	    String[] parts = str.split("\\|");
//	    int[] array = new int[parts.length];
//	    for (int i = 0; i < parts.length; i++) {
//	        array[i] = Integer.parseInt(parts[i]);
//	    }
//	    return array;
//	}
//	private int[][] stringTo2DArray(String str) {
//	    String[] parts = str.split(";");
//	    int[][] array = new int[parts.length][];
//	    for (int i = 0; i < parts.length; i++) {
//	        array[i] = stringToArray(parts[i]);
//	    }
//	    return array;
//	}
//
	//	Singleton -------------------------------
	protected static Load_game getInstance() {
		if (instance == null) {
			instance = new Load_game();
		}
		return instance;
	}

	
}
//	escreve o turno

// p00 p01 p02 p03 | p10 p11 p12 p13 | p20 p21 p22 p23 | p30 p31 p32 p33 | turn

//	p00 = [index, lista]
//	turn = ply id