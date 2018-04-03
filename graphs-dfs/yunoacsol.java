package graph;

import java.util.LinkedList;
import java.util.Scanner;

public class yunoacsol {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int orig = reader.nextInt();
			int dest = reader.nextInt();
			g[orig].add(dest);
		}
		hasCycle();
		System.out.println(hasCycle ? "yes" : "no");
		reader.close();
	}
	
	static final int UNV = 0, OPEN = 1, CLOSED = 2;
	static LinkedList<Integer>[] g;
	static int[] state;
	static Integer[] parent;
	static boolean hasCycle;

	static void hasCycle() {
	    hasCycle = false;
	    state = new int[g.length];
	    parent = new Integer[g.length];
	    for(int u = 0; u < g.length; u++) {
	        if(state[u] == UNV) {
	            dfsVisit(u);
	        }
	    }
	}

	static void dfsVisit(int u) {
	    state[u] = OPEN;
	    for(int v : g[u]) {
	        if(state[v] == UNV) {
	            parent[v] = u;
	            dfsVisit(v);
	        } else if(state[v] == OPEN) {
	            // (u, v) is a cycle edge, the graph contains a cycle
	            hasCycle = true;
	        }
	    }
	    state[u] = CLOSED;
	}

}
