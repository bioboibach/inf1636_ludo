package modal;

public class Facade {
	
	public static int roll() {
		return Dado.getInstance().roll();
	}
}
