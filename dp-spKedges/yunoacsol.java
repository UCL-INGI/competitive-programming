
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class yunoacsol {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		int s = reader.nextInt();
		int t = reader.nextInt();
		int k = reader.nextInt();
		LinkedList<Edge>[] inEdges = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			inEdges[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			int w = reader.nextInt();
			inEdges[v].add(new Edge(u, w));
			inEdges[u].add(new  Edge(v, w));
		}
		double[][] dist = solve(inEdges, s);
		if(dist[t][k] == Double.POSITIVE_INFINITY) {
			System.out.println("impossible");
		} else {
			System.out.println((int)dist[t][k]);
		}
		reader.close();
	}
	
	static double[][] solve(LinkedList<Edge>[] inEdges, int s) {
		double[][] dp = new double[inEdges.length][inEdges.length];
		for(int v = 0; v < inEdges.length; v++) {
			Arrays.fill(dp[v], Double.POSITIVE_INFINITY);			
		}
		dp[s][0] = 0;
		for(int i = 1; i < inEdges.length; i++) {
			for(int v = 0; v < inEdges.length; v++) {
				dp[v][i] = dp[v][i - 1];
				for(Edge e : inEdges[v]) {
					int u = e.orig;
					dp[v][i] = Math.min(dp[v][i], dp[u][i - 1] + e.w);
				}
			}
		}
		return dp;
	}
	
	static class Edge {
		
		int orig;
		double w;
		
		public Edge(int orig, double w) {
			this.orig = orig;
			this.w = w;
		}
		
	}

}

