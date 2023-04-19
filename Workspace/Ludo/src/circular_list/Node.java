package circular_list;

public class Node {
	Node prev;
	Node next;
	Object obj;
	
	/*	Node = um tile do board
	 * 
	 * componentes:
	 * 		OBJECT o == Tile 
	 * 		NODE next
	 * */
	
	public Node(Object o, Node p, Node n) {
		prev = p;
		next = n;
		obj = o;
	}
	
	public Node get_next() {
		return next;
	}
	
	public Node get_previous() {
		return prev;
	}
	
	public Object get_content() {
		return obj;
	}
	
	public void set_next(Node n) {
		next = n;
		return;
	}
	
	public void set_prev(Node p) {
		prev = p;
		return;
	}
}
