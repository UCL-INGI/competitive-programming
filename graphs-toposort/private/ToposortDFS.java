package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ToposortDFS {
	
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
			int u = reader.nextInt();
			int v = reader.nextInt();
			g[u].add(v);
		}
		toposort();
		String ans = Arrays.toString(toposort);
		ans = ans.substring(1, ans.length() - 1);
		ans = ans.replaceAll(",", "");
		System.out.println(ans);
		reader.close();
	}

	static final int UNV = 0, OPEN = 1, CLOSED = 2;
	static LinkedList<Integer>[] g;
	static int[] state;
	static boolean foundCycle;

	static int[] toposort; // toposort will have the node indexes in topological order
	static int toposortIndex; // to keep track where to put the next node

	static void toposort() {
	    foundCycle = false;
	    state = new int[g.length];
	    toposort = new int[g.length];
	    toposortIndex = g.length - 1;
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
	            dfsVisit(v);
	        } else if(state[v] == OPEN) {
	            // (u, v) is a cycle edge, the graph contains a cycle
	            foundCycle = true;
	        }
	    }
	    // add u to the topological order
	    // note that we start from the end since we want to reverse the closing order
	    toposort[toposortIndex--] = u;
	    state[u] = CLOSED;
	}

	
}
