package modal;
import java.util.concurrent.ThreadLocalRandom;


class Dado {
	
	private static Dado instance;
	private int d1;
	
	protected static Dado getInstance() {
		if (instance == null) {
			instance = new Dado();
		}
		return instance;
	}
	
	protected int roll() {
		d1 = ThreadLocalRandom.current().nextInt(1, 7);
		return d1;
	}
	
	protected int set_roll_value(int v1) {
		d1 = v1;
		return v1;
	}
}