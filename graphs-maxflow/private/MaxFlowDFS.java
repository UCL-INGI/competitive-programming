package graph;

import java.util.LinkedList;
import java.util.Scanner;

public class MaxFlowDFS {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		int s = reader.nextInt();
		int t = reader.nextInt();
		ResidualGraph g = new ResidualGraph(n);
		for(int i = 0; i < m; i++) {
			int orig = reader.nextInt();
			int dest = reader.nextInt();
			int cap = reader.nextInt();
			g.connect(orig, dest, cap);	
		}
		int mf = maxflow(g, s, t);
		System.out.println(mf);
		reader.close();
	}

	static int maxflow(ResidualGraph g, int s, int t) {
		int maxflow = 0;
		pcap = Integer.MAX_VALUE;
		while(augmentFlowDFS(g, s, t, new boolean[g.n])) {
			maxflow += pcap;
			pcap = Integer.MAX_VALUE;
		}
		return maxflow;
	}

	static int pcap;

	static boolean augmentFlowDFS(ResidualGraph g, int cur, int t, boolean[] visited) {
		if(cur == t) return true;
		visited[cur] = true;
		for(FlowEdge e : g.outE[cur]) {
			if(e.cap > 0 && !visited[e.dest]) {
				pcap = Math.min(pcap, e.cap);
				if(augmentFlowDFS(g, e.dest, t, visited)) {
					e.push(pcap);
					return true;
				}
			}
		}
		return false;
	}

	static class ResidualGraph {

		LinkedList<FlowEdge>[] outE;
		int n;

		@SuppressWarnings("unchecked")
		public ResidualGraph(int n) {
			this.n = n;
			outE = new LinkedList[n];
			for(int u = 0; u < n; u++) {
				outE[u] = new LinkedList<>();
			}
		}

		public void connect(int u, int v, int cap) {
			FlowEdge e = new FlowEdge(u, v, cap);
			// create residual edge
			FlowEdge r = new FlowEdge(v, u, 0);
			e.residual = r;
			r.residual = e;
			outE[u].add(e);
			outE[v].add(r);
		}

	}

	static class FlowEdge {

		FlowEdge residual;
		int orig, dest, cap;

		public FlowEdge(int u, int v, int cap) {
			this.orig = u;
			this.dest = v;
			this.cap = cap;
		}

		public void push(int flow) {
			cap -= flow;
			residual.cap += flow;
		}

	}

}
