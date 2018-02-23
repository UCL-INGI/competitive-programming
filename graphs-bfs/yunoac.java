
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {

	/*

5 4
0 1
1 2
2 3
3 4

11 10
0 1
0 2
2 3
0 4
4 5
5 6
0 7
7 8
8 9
9 10

	 */

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
			g[v].add(u);
		}
		int[] distance = distances(g, 0);
		int max = 0;
		for(int i = 0; i < g.length; i++) {
			max = Math.max(max, distance[i]);
		}
		System.out.println(max);
		reader.close();
	}

	static int[] distances(LinkedList<Integer>[] g, int s) {
		// initialize the queue and visited set
		Queue<Integer> Q = new LinkedList<>();
		Q.add(s);
		int[] distance = new int[g.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;
		// loop while there are nodes in the queue to process
		while(!Q.isEmpty()) {
			int u = Q.poll();
			// we are now processing node u
			for(int v : g[u]) {
				// visit edge (u, v)
				if(distance[v] == Integer.MAX_VALUE) {
					// node v has not yet been visited, add it
					Q.add(v);
					// set the distance of v
					distance[v] = 1 + distance[u];
				}
			}
		}
		// return the distances from s
		return distance;
	}

}
