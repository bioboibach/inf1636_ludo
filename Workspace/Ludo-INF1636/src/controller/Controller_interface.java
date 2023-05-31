package controller;

import modal.Modal_interface;


public class Controller_interface {
	
	private static Controller_interface instance;

	private Controller_interface() {}
	
	public int roll() {
		return Modal_interface.getInstance().roll();
	}
	
	public static Controller_interface getInstance() {
		if (instance == null) {
			instance = new Controller_interface();
		}
		return instance;
	}
}
