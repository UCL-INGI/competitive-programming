
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			g[u].add(v);
		}
		ArrayList<Integer> order = toposort(g);
		String ans = order.toString();
		ans = ans.substring(1, ans.length() - 1);
		ans = ans.replaceAll(",", "");
		System.out.println(ans);
		reader.close();
	}

	static ArrayList<Integer> toposort(LinkedList<Integer>[] g) {
		// compute the in-degrees
		int[] indeg = new int[g.length];
		for(int u = 0; u < g.length; u++) {
			for(int v : g[u]) {
				indeg[v] += 1;
			}
		}
		// initialize the queue with 0 in-degree nodes
		Queue<Integer> Q = new LinkedList<>();
		for(int u = 0; u < g.length; u++) {
			if(indeg[u] == 0) {
				Q.add(u);
			}
		}
		ArrayList<Integer> order = new ArrayList<>();
		while(!Q.isEmpty()) {
			int u = Q.poll();
			// add u as the next node in the topological order
			order.add(u);
			// we are now processing node u
			for(int v : g[u]) {
				// visit edge (u, v)
				indeg[v] -= 1;
				if(indeg[v] == 0) {
					// node v has now in-degree 0, add it to the queue
					Q.add(v);
				}
			}
		}
		return order;
	}
	
}
