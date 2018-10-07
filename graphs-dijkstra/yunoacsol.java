
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class yunoacsol {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		int s = reader.nextInt();
		int t = reader.nextInt();
		LinkedList<Edge>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int orig = reader.nextInt();
			int dest = reader.nextInt();
			int cost = reader.nextInt();
			g[orig].add(new Edge(orig, dest, cost));
			g[dest].add(new Edge(dest, orig, cost));
		}
		double[] dist = disjktra(g, s);
		if(dist[t] == Double.POSITIVE_INFINITY) {
			System.out.println("unreachable");
		} else {
			System.out.println((int)dist[t]);
		}
		reader.close();
	}


	public static class Edge {

		public int orig, dest, cost;

		public Edge(int orig, int dest, int cost) {
			this.orig = orig;
			this.dest = dest;
			this.cost = cost;
		}

		public String toString() {
			return String.format("(%d, %d, %d)", orig, dest, cost);
		}

	}

	public static class EdgeCmp implements Comparator<Edge> {

		private double[] dist;

		public EdgeCmp(double[] dist) {
			this.dist = dist;
		}

		public int compare(Edge e1, Edge e2) {
			double v1 = dist[e1.orig] + e1.cost;
			double v2 = dist[e2.orig] + e2.cost;
			return Double.compare(v1, v2);
		}

	}

	public static double[] disjktra(LinkedList<Edge>[] g, int s) {
		// initialize distances
		double[] dist = new double[g.length];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[s] = 0;
		// initialize PQ
		PriorityQueue<Edge> Q = new PriorityQueue<>(new EdgeCmp(dist));
		for(Edge e : g[s]) Q.add(e);
		while(!Q.isEmpty()) {
			Edge mine = Q.poll();
			// check whether the edge goes out of S
			if(dist[mine.dest] == Double.POSITIVE_INFINITY) {
				dist[mine.dest] = dist[mine.orig] + mine.cost;
				for(Edge e : g[mine.dest]) {
					Q.add(e);
				}
			}
		}
		return dist;
	}

}
