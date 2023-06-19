package controller;

import modal.Modal_interface;
import java.io.IOException;

public class Controller_interface {
	
	boolean dadosRolados = false;
	
	private static Controller_interface instance;

	private Controller_interface() {}
	
	public int roll() {
		dadosRolados = true;
		return Modal_interface.getInstance().get_roll();
	}
	
	public void save_game() throws IOException{
		Save_game.getInstance().save();
	}
	
	public void load_game() {
		Load_game.getInstance().load();
	}
	
	public static Controller_interface getInstance() {
		if (instance == null) {
			instance = new Controller_interface();
		}
		return instance;
	}


//	Metdoos get --------------------------------------------
	
	
//	TODO
//	fazer o dadosRolados  voltar a ser false no final do turno
	public boolean get_dadosRolados() {
		return dadosRolados;
	}
}
