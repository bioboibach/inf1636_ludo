package view;

import java.awt.Color;

import controller.ControllerAPI;
import controller.ObservadoIF;
import controller.ObservadorIF;

public class ViewAPI implements ObservadorIF{
	private static ViewAPI instance;
	
//	private ControllerAPI control = ControllerAPI.getInstance();
	
	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK};
	
	boolean dadosRolados = false;
    
// ____________________________________________________________________________________________________________________________
//
			
    public ViewAPI(){}
	
//	Operacoes -------------------------------------------
	public void set_dadosRolados(boolean valor) {
		dadosRolados = valor;
	}
	
	public void newGame(){
//		control.new_game();
	}
	
	public void loadSavedGame(){
//		control.load_game();
	}
	
	public void SaveGame() {
//		control.save_game();
	}
	
	
	protected void executaTurno(int indice_path, int indice_final_path, int color) {
//		control.executaTurno(indice_path, indice_final_path, color);
	}
//	Metodos get --------------------------------------------
	public boolean get_dadosRolados() {
		return dadosRolados;
	}
	
	public static Color[] get_colors() {
		return COLORS;
	}
	
	
//	Implementacao da interface Observador ----------------
	public void notify(ObservadoIF o) {
		
		LudoBoard ludoBoard = new LudoBoard();
		
		Object[] info = (Object[]) o.get();
		
//		ludoBoard.updateBoardInfo(info); 
	}
	
//	Singleton ------------------------------------------
	public static ViewAPI getInstance() {
		if (instance == null) {
			instance = new ViewAPI();
		}
		return instance;
	}
		
}
