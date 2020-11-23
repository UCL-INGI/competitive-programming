public class LList<V> {
	
	// pointers to the first and last node
	private Node head, tail;
	private int size;
	
	public LList() {
		head = tail = null;
		size = 0;
	}
	
	public class Node {
		
		private V value;
		private Node prev, next;
		
		public Node(V value) {
			this.value = value;
		}
		
	}
	
	public int size() {
		return size;
	}
	
	public void addLast(V value) {
		// step 1. create a new node with the given value
		Node node = new Node(value);
		if(tail == null) { 
			// empty list case
			head = tail = node;
		} else {
			// step 2. make the next of the tail be the new node
			tail.next = node;
			// step 3. make the previous of the new node be the tail
			node.prev = tail;
			// step 4. make the new node the tail of the list
			tail = node;
		}
		size += 1;
	}
	
	
	public void removeLast() {
		if(size == 1) head = tail = null;
		else {
			// step 1. set the new tail to be the previous of the current one
			tail = tail.prev;
			// step 2. set the next of the tail to null
			tail.next = null;
		}
		size -= 1;
	}
	
	public void addFirst(V value) {
		// step 1. create a new node with the given value
		Node node = new Node(value);
		if(tail == null) {
			// empty list case
			head = tail = node;
		} else {
			// step 2. make the previous of the head to be the new node
			head.prev = node;
			// step 3. make next of the new node the head
			node.next = head;
			// step 4. make the new node the head of the list
			head = node;
		}
		size += 1;
	}
	
	public void removeFirst(V value) {
		if(size == 1) head = tail = null;
		else {
			// step 1. set the new head to be the next of the current one
			head = head.next;
			// step 2. set the previous of the head to null
			head.prev = null;
		}
		size -= 1;
	}
	
	public V getFirst() {
		return head.value;
	}
	
	public V getLast() {
		return tail.value;
	}

}
