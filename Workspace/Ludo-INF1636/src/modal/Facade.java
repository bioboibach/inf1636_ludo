package modal;


public class Facade {
	
	private static Facade instance;

	private Facade() {}
	
	public int roll() {
		return Dado.getInstance().roll();
	}
	
	public static Facade getInstance() {
		if (instance == null) {
			instance = new Facade();
		}
		return instance;
	}
}
