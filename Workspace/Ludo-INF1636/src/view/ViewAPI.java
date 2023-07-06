package view;

//import modal.ModalAPI;

public class ViewAPI {
	private static ViewAPI instance;
    //private ModelAPI modelAPI = ModelAPI.getInstance();
    
    public ViewAPI(){
        instance = this;
    }
	
    public static ViewAPI getInstance() {
        if (instance == null) {
            instance = new ViewAPI();
        }
        return instance;
    }
    
	public void drawPeca() {
		
	}
	
}
