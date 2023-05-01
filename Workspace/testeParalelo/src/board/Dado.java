package board;
import java.util.concurrent.ThreadLocalRandom;


public class Dado {
	private int d1;
	
	public int roll() {
		d1 = ThreadLocalRandom.current().nextInt(1, 7);
		return d1;
	}
	
	public int set_roll_value(int v1) {
		d1 = v1;
		return v1;
	}
}
