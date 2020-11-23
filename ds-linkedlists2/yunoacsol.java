
import java.util.Scanner;

/*
ab[cd]ef

a[b]c[d]e[f]g

 */

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String x = reader.nextLine();

		LList<Character> list = new LList<>();

		for(int i = 0; i < x.length(); i++) {
			if(x.charAt(i) == '[') list.home();
			else if(x.charAt(i) == ']') list.end();
			else {
				list.add(x.charAt(i));
			}
		}

		System.out.println(list.toString());
		reader.close();
	}

	static class LList<V> {

		// pointers to the first and last node
		public Node head, tail, cur;

		public LList() {
			head = tail = cur = new Node(null);
		}

		public class Node {

			private V value;
			private Node next;

			public Node(V value) {
				this.value = value;
			}

			public String toString() {
				return value.toString();
			}

		}

		public void add(V value) {
			Node node = new Node(value);
			if(cur == null) {
				head = tail = cur = node;
			} else if(cur == tail) {
				cur.next = node;
				cur = tail = node;
			} else {
				node.next = cur.next;
				cur.next = node;
				cur = node;
			}
		}

		public void home() {
			cur = head;
		}

		public void end() {
			cur = tail;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node c = head;
			while(c != null) {
				if(c.value != null) {
					sb.append(c.value.toString());					
				}
				c = c.next;
			}
			return sb.toString();
		}

	}

}
