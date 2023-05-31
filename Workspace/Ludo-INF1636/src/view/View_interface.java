package view;



public class View_interface {
	private static View_interface instance;
	
	private View_interface() {}
	
	public View_interface getInstance() {
		if (instance == null) {
			instance = new View_interface();
		}
		return instance;
	}
}
