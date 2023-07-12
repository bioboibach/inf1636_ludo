package view;

import controller.ObservadoIF;
import controller.ObservadorIF;
import modal.ModalAPI;

public class ViewAPI implements ObservadorIF{
	private static ViewAPI instance;
	
	boolean dadosRolados = false;
    
    public ViewAPI(){}
	
//	Operacoes -------------------------------------------
	public void set_dadosRolados(boolean valor) {
		dadosRolados = valor;
	}
    
	
//	Metodos get --------------------------------------------
	public boolean get_dadosRolados() {
		return dadosRolados;
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
