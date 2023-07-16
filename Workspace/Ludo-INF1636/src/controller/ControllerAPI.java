package controller;

import modal.ModalAPI;
import java.io.IOException;

public class ControllerAPI {
	
	private static ControllerAPI instance;
	private ModalAPI modalInst = ModalAPI.getInstance();

	private int currentPlayer;
	
// ____________________________________________________________________________________________________________________________
//
	private ControllerAPI() {}
	
	
	//	Operacoes -------------------------------------------	
	public void newGame(){
		System.out.println("Iniciando nova partida...");
		modalInst.new_game();
	}	
	public void load_game() {	// throws IOException
		System.out.println("Carregando a partida salva...");
	    try {
	        Load_game.getInstance().load();
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
	public void save_game() {	// throws IOException
		System.out.println("Salvando a partida...");
		try {
	        Save_game.getInstance().save();
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}

	public void executaTurno(int pathIndex, int finalPathIndex, int diceVal){
		modalInst.next_turn();
		modalInst.set_dado(diceVal);
		
		int listType;
		int listIndex;		
		
		if(finalPathIndex != -1){
			listIndex = finalPathIndex;
			listType = 0;	// path
		}
		else if(pathIndex != -1) {
			listIndex = pathIndex;
			listType = 2;	// finalPath ~ retaFinal
		}
		else {
			listIndex = -1;
			listType = 1;	// casa inicial
		}
		
		modalInst.set_positions(currentPlayer, listIndex, listType);
		
		instance.nextPlayer();
		
		modalInst.run_turn();
	}
	
	public int roll() {
		return modalInst.roll();
	}
	
	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % 4;
	}

	//	GET --------------------------------------------
	protected int get_currentPlayer() {
		return currentPlayer;
	}

	//	Singleton ------------------------------------------


	public static ControllerAPI getInstance() {
		if (instance == null) {
			instance = new ControllerAPI();
		}
		return instance;
	}

}
