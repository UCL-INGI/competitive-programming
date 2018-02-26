
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
		Integer[] comp = connectedComponents(g);
		int nnCmp = comp[0];
		for(int i = 0; i < n; i++) {
			nnCmp = Math.max(nnCmp, comp[i]);
		}
		System.out.println(nnCmp);
		int q = reader.nextInt();
		for(int i = 0; i < q; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			if(comp[u].equals(comp[v])) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
		reader.close();
	}

	static Integer[] connectedComponents(LinkedList<Integer>[] g) {
		Integer[] comp = new Integer[g.length];
		int compId = 0;
		for(int s = 0; s < g.length; s++) {
			// check is s is already labeled
			if(comp[s] != null) continue;
			// s is not labeled, perform a BFS from it to compute its component
			// initialize the queue and visited set
			Queue<Integer> Q = new LinkedList<>();
			Q.add(s);
			// set the component of s
			comp[s] = compId;
			// loop while there are nodes in the queue to process
			while(!Q.isEmpty()) {
				int u = Q.poll();
				// we are now processing node u
				for(int v : g[u]) {
					// visit edge (u, v)
					if(comp[v] == null) {
						// node v has not yet been visited, add it
						Q.add(v);
						// set the component of v
						comp[v] = compId;
					}
				}
			}
			// finished labeling the component, increment the component id
			compId += 1;
		}
		return comp;
	}
	
}
