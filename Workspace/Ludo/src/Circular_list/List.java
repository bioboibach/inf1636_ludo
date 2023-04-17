package Circular_list;

public class List {
	Object o;
	Node next;
	
	public List (Object o) {
		Node node = new Node(o, null, null);
		node.set_next(node);
		node.set_prev(node);
		return;
	}
	
	public void append(Object o) {
		Node node = new Node(o, null,this.prev);
		
	}
	
	public List get_first() {
		
		return 
	}
	
	public void remove() {
		
	}
	
	public boolean is_empty() {
		return n.get_next() == null;
	}
	
	
}
