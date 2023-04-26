package circular_list;

public class List {
	private static Node head = new Node(null, null, null);
	public static int list_size = 0;
	
	Node curr;
	Node ini;
	
	public void start(Object o) {
		ini = new Node(o, null, null);
		head.set_next(ini);
		ini.set_prev(ini);
		ini.set_next(ini);
		curr = ini;
		list_size++;
	}
	
	public void append(Object o) {
		Node new_node = new Node(o, curr, curr.get_next()); 
		
		curr.set_next(new_node);
		
		new_node.next.set_prev(new_node);
		
		curr = new_node;
		list_size++;
	}
	
	public Object get_first() {
		return ini.get_content();
	}
	public Object get_current() {
		return curr.get_content();
	}
	
	public void go_next() {
		curr = curr.get_next();
		return;
	}
	
	public void go_back() {
		curr = curr.get_previous();
		return;
	}
	
	public void remove() {
		curr.prev.set_next(curr.next);
		curr.next.set_prev(curr.prev);
		curr = curr.next;
		list_size--;
	}
	
	public int len() {
		return list_size;
	}
}
