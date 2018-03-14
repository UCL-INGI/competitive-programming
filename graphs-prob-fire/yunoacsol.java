import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*


4 4
####
#JF#
#..#
#..#

3 3
###
#J.
#.F

 */

public class yunoacsol {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] data = reader.readLine().split(" ");
		int n = Integer.parseInt(data[0]);
		int m = Integer.parseInt(data[1]);
		char[][] maze = new char[n][m];
		for(int i = 0; i < n; i++) {
			maze[i] = reader.readLine().toCharArray();
		}
		Queue<int[]> fire = new LinkedList<>();
		int[] joe = null;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(maze[i][j] == 'F') {
					fire.add(new int[] {i, j});
					maze[i][j] = '.';
				} else if(maze[i][j] == 'J') {
					joe = new int[] {i, j};
				}
			}
		}
		int[][] fireDist = bfsFire(n, m, maze, fire);
		int ans = bfsJoe(n, m, maze, joe, fireDist);
		if(ans == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(1 + ans);
		}
	}

	static int[][] dir = new int[][] {
		new int[] {-1, 0},
		new int[] {1, 0},
		new int[] {0, -1},
		new int[] {0, 1}
	};

	static int[][] bfsFire(int n, int m, char[][] maze, Queue<int[]> fire) {
		int[][] dist = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		for(int[] p : fire) {
			dist[p[0]][p[1]] = 0;
		}
		while(!fire.isEmpty()) {
			int[] cur = fire.poll();
			for(int[] d : dir) {
				int i = cur[0] + d[0];
				int j = cur[1] + d[1];
				if(!(0 <= i && i < n && 0 <= j && j < m)) continue;
				if(maze[i][j] == '#') continue;
				if(dist[i][j] != Integer.MAX_VALUE) continue;
				dist[i][j] = 1 + dist[cur[0]][cur[1]];
				fire.add(new int[] {i, j});
			}
		}
		return dist;
	}

	static int bfsJoe(int n, int m, char[][] maze, int[] joe, int[][] fireDist) {
		int[][] dist = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[joe[0]][joe[1]] = 0;
		Queue<int[]> Q = new LinkedList<>();
		Q.add(joe);
		int min = Integer.MAX_VALUE;
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			if(cur[0] == 0 || cur[0] == n - 1 || cur[1] == 0 || cur[1] == m - 1) {
				min = Math.min(min, dist[cur[0]][cur[1]]);
			}
			for(int[] d : dir) {
				int i = cur[0] + d[0];
				int j = cur[1] + d[1];
				if(!(0 <= i && i < n && 0 <= j && j < m)) continue;
				if(maze[i][j] == '#') continue;
				if(dist[i][j] != Integer.MAX_VALUE) continue;
				if(fireDist[i][j] <= 1 + dist[cur[0]][cur[1]]) continue;
				dist[i][j] = 1 + dist[cur[0]][cur[1]];
				Q.add(new int[] {i, j});
			}
		}
		return min;
	}

}
