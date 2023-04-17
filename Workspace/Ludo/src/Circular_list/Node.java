package Circular_list;

public class Node {
	Node next;
	Node prev;
	Object obj;
	
	/*	Node = um tile do board
	 * 
	 * componentes:
	 * 		OBJECT o == Tile 
	 * 		NODE next
	 * */
	
	public Node(Object o,Node n, Node p) {
		next = n;
		prev = p;
		obj = o;
	}
	
	public Node get_next() {
		return next;
	}
	
	public Node get_previous() {
		return next;
	}
	
	public Object get_content() {
		return obj;
	}
	
	public void set_next(Node n) {
		next = n;
	}
	
	public void set_prev(Node p) {
		prev = p;
	}
}
