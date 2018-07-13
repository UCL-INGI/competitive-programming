
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

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
		int[] order = toposort(g);
		initDFS(g);
		int count = 0;
		for(int x : order) {
			if(state[x] == UNV) {
				count += 1;
				dfsVisit(g, x, VIS);
			}
		}
		System.out.println(count);
		reader.close();
	}

	static final int UNV = 0, VIS = 1;
	static int[] state;
	static int[] toposort;
	static int toposortIndex;

	static void initDFS(LinkedList<Integer>[] g) {
		toposort = new int[g.length];
		toposortIndex = g.length - 1;
		state = new int[g.length];
	}

	static int[] toposort(LinkedList<Integer>[] g) {
		initDFS(g);
		for(int u = 0; u < g.length; u++) if(state[u] == UNV) {
			dfsVisit(g, u, VIS);
		}
		return Arrays.copyOf(toposort, toposort.length);
	}

	static void dfsVisit(LinkedList<Integer>[] g, int u, int sccLabel) {
		state[u] = sccLabel;
		for(int v : g[u]) if(state[v] == UNV) {
			dfsVisit(g, v, sccLabel);		
		}
		toposort[toposortIndex--] = u;
	}

}
