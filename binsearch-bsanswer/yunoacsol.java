package binSearch;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for(int i = 0; i < n; i++) {
			x[i] = reader.nextInt();
			y[i] = reader.nextInt();
		}
		int r = solve(x, y) + 1;
		System.out.println(r);
		reader.close();
	}
	
	static int solve(int[] x, int[] y) {
		int L = 0;
		int R = 10000;
		while(R - L >= 2) {
			int M = (L + R) / 2;
			if(check(M, x, y)) L = M;
			else R = M;
		}
		return L;
	}
	
	@SuppressWarnings("unchecked")
	static boolean check(int r, int[] x, int[] y) {
		// build the graph
		int n = x.length;
		LinkedList<Integer>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				long dx = x[i] - x[j];
				long dy = y[i] - y[j];
				long sqdist = dx * dx + dy * dy;
				if(sqdist <= r * r) {
					g[i].add(j);
				}
			}
		}
		// check connectivity
		Queue<Integer> Q = new LinkedList<>();
		Q.add(0);
		BitSet visited = new BitSet();
		visited.set(0);
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			for(int next : g[cur]) {
				if(!visited.get(next)) {
					visited.set(next);
					Q.add(next);
				}
			}
		}
		return visited.cardinality() != n;
	}
	
}
