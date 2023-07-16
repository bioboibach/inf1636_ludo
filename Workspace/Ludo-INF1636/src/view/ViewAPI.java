package view;

import java.awt.Color;

import controller.ObservadoIF;
import controller.ObservadorIF;

public class ViewAPI implements ObservadorIF{
	private static ViewAPI instance;
	
	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK};
	
	boolean dadosRolados = false;
    
// ____________________________________________________________________________________________________________________________
//
			
    public ViewAPI(){}
	

    //	SET ----------------------------------------
	public void set_dadosRolados(boolean valor) {
		dadosRolados = valor;
	}

	//	GET ----------------------------------------
	public boolean get_dadosRolados() {
		return dadosRolados;
	}
	
	public static Color[] get_colors() {
		return COLORS;
	}
	
	
	//	Implementacao da interface Observador ------
	public void notify(ObservadoIF o) {
		LudoBoard ludoBoard = LudoBoard.getInstance();
		ludoBoard.updateBoardInfo(); 
	}
	
	//	Singleton ----------------------------------
	public static ViewAPI getInstance() {
		if (instance == null) {
			instance = new ViewAPI();
		}
		return instance;
	}
		
}
