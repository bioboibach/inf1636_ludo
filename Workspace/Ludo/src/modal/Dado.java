package modal;
import java.util.concurrent.ThreadLocalRandom;


public class Dado {
	private int d1;
	private int d2;
	
	public int roll() {
		d1 = ThreadLocalRandom.current().nextInt(1, 7);
		d2 = ThreadLocalRandom.current().nextInt(1, 7);
		
		return d1 + d2;
	}
	
	public int set_roll_value(int v1, int v2) {
		d1 = v1;
		d2 = v2;
		return v1 + v2;
	}
	
}
