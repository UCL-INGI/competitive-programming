import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class yunoacsol {

	public static void main(String[] args) throws IOException {
		solveStdin();
	}

	static void solveAll(int t1, int t2) throws IOException {
		for(int t = t1; t<= t2; t++) {
			long tStart = System.nanoTime();
			Scanner reader = new Scanner(new FileReader("E/T" + t + "/in" + t));
			// read input
			int n = reader.nextInt();
			int m = reader.nextInt();
			@SuppressWarnings("unchecked")
			LinkedList<Integer>[] g = new LinkedList[n];
			for (int i = 0; i < n; i++) {
				g[i] = new LinkedList<Integer>();
			}
			for (int i = 0; i < m; i++) {
				int o = reader.nextInt();
				int d = reader.nextInt();
				g[o].add(d);
				g[d].add(o);
			}
			int start = reader.nextInt();
			int pickup = reader.nextInt();
			int delivery = reader.nextInt();

			
			BufferedWriter writer = new BufferedWriter(new FileWriter("E/T" + t + "/out" + t));
			writer.write(solve(g, start, pickup, delivery) + "");
			writer.newLine();
			writer.close();
			long tEnd = System.nanoTime();
			System.out.println((double)(tEnd - tStart) / 1e9);
		}
	}

	static void solveStdin() {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] g = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			g[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int o = reader.nextInt();
			int d = reader.nextInt();
			g[o].add(d);
			g[d].add(o);
		}
		int start = reader.nextInt();
		int pickup = reader.nextInt();
		int delivery = reader.nextInt();
		int sol = solve(g, start, pickup, delivery);
		System.out.println(sol);
	}

	// O(V + E)
	static int solve(LinkedList<Integer>[] g, int start, int pickup, int delivery) {
		Integer[] ds = bfs(g, start);
		Integer[] dp = bfs(g, pickup);
		Integer[] dd = bfs(g, delivery);
		int sol = Integer.MAX_VALUE;
		for(int i = 0; i < g.length; i++)
			if(ds[i] != null) sol = Math.min(sol, ds[i] + dp[i] + dd[i]);
		return sol;
	}

	// O(V + E)
	static Integer[] bfs(LinkedList<Integer>[] g, int s) {
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);
		Integer[] d = new Integer[g.length];
		d[s] = 0;
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			for(int u : g[cur]) {
				if(d[u] == null) {
					d[u] = 1 + d[cur];
					Q.add(u);
				}
			}
		}
		return d;
	}

}

