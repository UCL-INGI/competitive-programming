package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MaxFlowWA {

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
		int flow;
		while((flow = augmentFlowBFS(g, s, t)) != 0) {
			maxflow += flow;
		}
		return maxflow;
	}

	static int augmentFlowBFS(ResidualGraph g, int s, int t) {
		// find a s-t path in g of positive capacity
		// initialize path capacities
		// pathcap[u] = capacity of the path from s to u
		int[] pathcap = new int[g.n];
		Arrays.fill(pathcap, Integer.MAX_VALUE);
		// initialize parents to build the paths
		FlowEdge[] parent = new FlowEdge[g.n];
		// initialize BFS queue
		Queue<Integer> Q = new LinkedList<>();
		Q.add(s);
		// perform BFS to find a path to t
		while(!Q.isEmpty() && parent[t] == null) {
			int cur = Q.poll();
			// loop over the edges out of cur to expand the paths
			for(FlowEdge e : g.outE[cur]) {
				if(e.dest != s && e.cap > 0 && parent[e.dest] == null) {
					// we found an unvisited node e.dest
					parent[e.dest] = e;
					pathcap[e.dest] = Math.min(pathcap[cur], e.cap);
					Q.add(e.dest);
				}
			}
		}
		// check whether a path was found
		if(parent[t] == null) return 0;
		// we found a path, update the flow
		int flow = pathcap[t];
		// push the flow on the path
		int cur = t;
		while(parent[cur] != null) {
			parent[cur].push(flow);
			cur = parent[cur].orig;
		}
		return flow;
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
			outE[u].add(e);
		}

	}

	static class FlowEdge {

		int orig, dest, cap;

		public FlowEdge(int u, int v, int cap) {
			this.orig = u;
			this.dest = v;
			this.cap = cap;
		}

		public void push(int flow) {
			cap -= flow;
		}

	}

}
