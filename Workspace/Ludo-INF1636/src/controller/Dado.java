package controller;
import java.util.concurrent.ThreadLocalRandom;


class Dado {
	
	private static Dado instance;
	
	protected int roll() {
		return ThreadLocalRandom.current().nextInt(1, 7);
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
