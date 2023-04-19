package Modal;
import Circular_list.*;

public class Main {

	public static void main(String[] args) {
		List l = new List();
		Tile t1 = new Tile(1, 0, 0, null, null, 2);
		Tile t2 = new Tile(2, 0, 0, null, null, 2);
		Tile t3 = new Tile(3, 0, 1, null, null, 2);
		Tile t4 = new Tile(4, 0, 1, null, null, 2);
		
		l.start(t1);
		l.append(t2);
		l.append(t3);
		l.append(t4);
		
		l.go_back();
		l.remove();
		
		for (int count = 0; count < l.len(); count++) {
			System.out.println(((Tile)l.get_current()).get_id());
			l.go_back();
		}
		
		
		return;
	}

}
