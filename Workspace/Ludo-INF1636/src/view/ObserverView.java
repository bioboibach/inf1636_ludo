package view;

import controller.Observer;
import controller.Observable;

public class ObserverView implements Observer {	
	
	private static ObserverView viewInstace;

	
	protected ObserverView() {}
	
	private int 	current_peca		;
	private int 	current_player 		;
	private int 	current_dado 		;
	private int[]	podio				;
	private int		valor0				;
	private int		valor1				;
	
    public void notify(Observable o) {
    	Object[] dados = (Object[]) o.get();
    	this.current_peca 	=  (int) 		dados[0];
    	this.current_player =  (int) 		dados[1];
    	this.current_dado 	=  (int) 		dados[2];
    	this.podio			=  (int[])		dados[3];
    	this.valor0			=  (int)		dados[4];
    	this.valor1			=  (int)		dados[5];
    }
    
    
//	Singleton ------------------------------------------
	public static ObserverView getInstance() {
		return viewInstace;
	}
}