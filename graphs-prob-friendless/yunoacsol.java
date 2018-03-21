
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {
	
/*

4 4
3 0
0 1
2 3
1 2

 */

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
			int x = reader.nextInt();
			int y = reader.nextInt();
			g[x].add(y);
		}
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for(int x = 0; x < n; x++) {
			if(distance[x] == Integer.MAX_VALUE) {
				distance[x] = 0;
				Queue<Integer> Q = new LinkedList<>();
				Q.add(x);
				while(!Q.isEmpty()) {
					int cur = Q.poll();
					for(int next : g[cur]) {
						if(distance[next] == Integer.MAX_VALUE) {
							distance[next] = 1 + distance[cur];
							Q.add(next);
						}
					}
				}
			}
		}
		boolean ok = true;
		for(int x = 0; ok && x < n; x++) {
			for(int y : g[x]) {
				if(distance[x] % 2 == distance[y] % 2) {
					ok = false;
					break;
				}
			}
		}
		System.out.println(ok ? "yes" : "no");
		reader.close();
	}
	
}
