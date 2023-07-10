package view;

import modal.ModalAPI;

public class ViewAPI {
	private static ViewAPI instance;
    private ModalAPI modelAPI = ModalAPI.getInstance();
	
	boolean dadosRolados = false;
    
    public ViewAPI(){
        instance = this;
    }
	
//	Operacoes -------------------------------------------
    
	public void set_dadosRolados(boolean valor) {
		dadosRolados = valor;
	}
    
//    public void showMessage(){
////        gameView.showMessage(modelAPI.getWinner());
//    }
//
//    public void showRanking(){
////        gameView.showRanking(modelAPI.playersResults());
//    }
//
//    public void showWarning(String warning){
////        gameView.showWarning(warning);
//    }
//
//    public MenuSubscriber getMenuSubscriber() {
////        return gameView.getGameMenu().getSubscriber();
//    }
//
//    public BoardSubscriber getBoardSubscriber() {
////        return gameView.getGameBoard().getBoardSubscriber();
//    }
//
//    public void endGameMessage(String winner){
////        gameView.showMessage(winner);
//    }
    
    
	public void drawPeca() {
		
	}
	
//	Metodos get --------------------------------------------
	public boolean get_dadosRolados() {
		return dadosRolados;
	}
	
//	Singleton ------------------------------------------
	public static ViewAPI getInstance() {
        if (instance == null) {
            instance = new ViewAPI();
        }
        return instance;
    }
    
}
