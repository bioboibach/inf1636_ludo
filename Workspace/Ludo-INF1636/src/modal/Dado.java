package modal;
import java.util.concurrent.ThreadLocalRandom;


class Dado {
	
	private static Dado instance;
	
	protected int roll() {
		int roll = ThreadLocalRandom.current().nextInt(1, 7); 
		return roll;
	}
	
	protected int set_roll_value(int v1) {
		return v1;
	}
	
	protected static Dado getInstance() {
		if (instance == null) {
			instance = new Dado();
		}
		return instance;
	}
}
