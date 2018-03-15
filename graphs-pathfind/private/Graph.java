import java.util.Scanner;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Graph {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // number of nodes
		int e = sc.nextInt(); // number of edges
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < e; i++) {
			int a = sc.nextInt() - 1; // start index at 0
			int b = sc.nextInt() - 1; // start index at 0
			g[a].add(b);
		}
		// methods
		int s = sc.nextInt() - 1; // start index at 0
		int t = sc.nextInt() - 1; // start index at 0
		
		ArrayList<Integer>ret = findPath(g,s,t);
		if (ret == null) {
			System.out.println("impossible");
		} else {
			Iterator<Integer>it = ret.iterator();
			System.out.print(it.next());
			while (it.hasNext()) {
				System.out.print(" " + it.next());
			}
			System.out.println();
		}
 		sc.close();
	}
	
	static boolean existsPath(LinkedList<Integer>[]g, int s, int t) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		BitSet visited = new BitSet();
		visited.set(s);
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int a : g[curr]) {
				if(!visited.get(a)) {
					q.add(a);
					visited.set(a);
				}
			}
		}
		return visited.get(t);
	}
	
	static ArrayList<Integer> findPath(LinkedList<Integer>[]g, int s, int t) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		BitSet visited = new BitSet();
		visited.set(s);
		Integer[] parent = new Integer[g.length];
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int a : g[curr]) {
				if(!visited.get(a)) {
					q.add(a);
					visited.set(a);
					parent[a] = curr;
				}
			}
		}
		if(!visited.get(t))
			return null;
		ArrayList<Integer> path = new ArrayList<>();
		Integer curr = t;
		while(curr != null) {
			path.add(curr + 1); // add one (from the -1 at the beginning)
			curr = parent[curr];
		}
		Collections.reverse(path);
		return path;
	}
}

