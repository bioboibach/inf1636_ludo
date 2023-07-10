package view;

import controller.Observavel;

public class Observador{	
	
	private static Observador viewInstace;

	
	protected Observador() {}
	
	private int 	current_peca;
	private int 	current_player;
	private int 	current_dado;
	private int[]	podio;
	private int		indice_array_cor;
	private int		array_cor;
	
    public void notify(Observavel o) {
    	Object[] dados = (Object[]) o.get();
    	this.current_peca 	=  (int) 		dados[0];
    	this.current_player =  (int) 		dados[1];
    	this.current_dado 	=  (int) 		dados[2];
    	this.podio			=  (int[])		dados[3];
    	this.indice_array_cor =(int)		dados[4];
    	this.array_cor		=  (int)		dados[5];
    }
    
    
//	Singleton ------------------------------------------
	public static Observador getInstance() {
		return viewInstace;
	}
}