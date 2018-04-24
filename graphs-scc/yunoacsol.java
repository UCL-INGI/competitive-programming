
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/*
16 26
0 1
1 3
3 2
2 0
1 2
0 5
5 4
4 6
6 5
3 4
1 8
3 7
7 8
8 11
11 9
9 7
7 10
10 11
7 12
10 14
11 15
12 14
14 12
13 15
12 13
15 14


 */

public class yunoacsol {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		LinkedList<Integer>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			g[u].add(v);
		}
		int[] c = scc(g);
		int nbscc = 0;
		for(int i = 0; i < c.length; i++) {
			nbscc = Math.max(nbscc, c[i]);
		}
		System.out.println(nbscc);
		String out = Arrays.toString(c);
		out = out.substring(1, out.length() - 1);
		out = out.replace(", ", " ");
		System.out.println(out);
		reader.close();
	}

	static final int UNV = 0;
	// scc will contain the scc label of the nodes, scc[i] = scc[j] iff i, j are on the same scc
	static int[] scc;
	static int[] toposort; // toposort will have the node indexes in topological order
	static int toposortIndex; // to keep track where to put the next node


	@SuppressWarnings("unchecked")
	static LinkedList<Integer>[] transpose(LinkedList<Integer>[] g) {
		LinkedList<Integer>[] gt = new LinkedList[g.length];
		for(int i = 0; i < g.length; i++) {
			gt[i] = new LinkedList<>();
		}
		for(int i = 0; i < g.length; i++) {
			for(int j : g[i]) {
				gt[j].add(i);
			}
		}
		return gt;
	}

	static int[] scc(LinkedList<Integer>[] g) {
		// compute the reverse graph
		LinkedList<Integer>[] gt = transpose(g);
		// get the order
		int[] order = toposort(gt);
		// reset dfs data to visit the SCC's
		initDFS(g);
		// initialize the SCC label, nodes with same label are on the same SCC
		int sccLabel = 1;
		// loop over the nodes in order to visit the SCC's
		for(int u : order) if(scc[u] == UNV) {
			dfsVisit(g, u, sccLabel++);
		}
		return Arrays.copyOf(scc, scc.length);
	}

	static void initDFS(LinkedList<Integer>[] g) {
		toposort = new int[g.length];
		toposortIndex = g.length - 1;
		scc = new int[g.length];
	}

	static int[] toposort(LinkedList<Integer>[] g) {
		initDFS(g);
		for(int u = 0; u < g.length; u++) if(scc[u] == UNV) {
			dfsVisit(g, u, 1);
		}
		return Arrays.copyOf(toposort, toposort.length);
	}

	static void dfsVisit(LinkedList<Integer>[] g, int u, int sccLabel) {
		scc[u] = sccLabel;
		for(int v : g[u]) if(scc[v] == UNV) {
			dfsVisit(g, v, sccLabel);		
		}
		toposort[toposortIndex--] = u;
	}

}
