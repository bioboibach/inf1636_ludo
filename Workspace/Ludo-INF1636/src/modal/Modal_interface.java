package modal;


public class Modal_interface {
	
	private static Modal_interface instance;

	private Modal_interface() {}
	
	public int roll() {
		return Dado.getInstance().roll();
	}
	
	public static Modal_interface getInstance() {
		if (instance == null) {
			instance = new Modal_interface();
		}
		return instance;
	}
}
