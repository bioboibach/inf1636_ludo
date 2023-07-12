package controller;

import modal.ModalAPI;
import java.io.IOException;

public class ControllerAPI {
	
	private static ControllerAPI instance;
	private ModalAPI modalInst = ModalAPI.getInstance();

	int currentPlayer;
	
	private ControllerAPI() {}
	
	
//	Operacoes -------------------------------------------	
	public void save_game() throws IOException{
		Save_game.getInstance().save();
	}
	
	public void load_game() throws IOException{
		Load_game.getInstance().load();
	}
	
	public void new_game(){
		modalInst.new_game();
	}
	
	public int roll() {
		return modalInst.roll();
	}
	
	public void executaTurno(int indice_path, int indice_final_path, int die_val){
		modalInst.next_turn();
		modalInst.set_dado(die_val);
		
		int index;
		int list_id;
		
		if(indice_final_path != -1){
			index = indice_final_path;
			list_id = 0;	// path
		}
		else if(indice_path != -1) {
			index = indice_path;
			list_id = 2 + currentPlayer;	// final_path ~ reta_final
		}
		else {
			index = -1;
			list_id = 1;	// casa inicial
		}
		
		modalInst.set_positions(currentPlayer, 1, index, list_id);
		
		instance.nextPlayer();
		
		modalInst.run_turn();
	}
	
	
	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % 4;
	}

	//	Metodos get --------------------------------------------


//	Singleton ------------------------------------------
	public static ControllerAPI getInstance() {
		if (instance == null) {
			instance = new ControllerAPI();
		}
		return instance;
	}

}
