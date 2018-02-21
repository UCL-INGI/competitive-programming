
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {

/*
11 11
###########
#....T#.#.#
#.#.###.#.#
#.#.#.....#
#.#.#.###.#
#.#.#.#.#.#
#.#.#.#S#.#
#.#.#.#.#.#
#.#.###.#.#
#.........#
###########
	
 */
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		char[][] maze = new char[n][];
		for(int i = 0; i < n; i++) {
			maze[i] = reader.next().toCharArray();
		}
		int[] start = null;
		int[] end = null;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(maze[i][j] == 'S') {
					start = new int[] {i, j};
					maze[i][j] = '.';
				} else if(maze[i][j] == 'T') {
					end = new int[] {i, j};
					maze[i][j] = '.';
				}
			}
		}
		ArrayList<int[]> path = findPath(n, m, maze, start, end);
		for(int[] pos : path) {
			System.out.println(pos[0] + " " + pos[1]);
		}
		reader.close();
	}
	
	static int[][] dir = new int[][] {
		new int[] {-1, 0},
		new int[] {1, 0},
		new int[] {0, -1},
		new int[] {0, 1}
	};
	
	static ArrayList<int[]> findPath(int n, int m, char[][] maze, int[] start, int[] end) {
		Queue<int[]> Q = new LinkedList<>();
		Q.add(start);
		boolean[][] visited = new boolean[n][m];
		int[][][] parent = new int[n][m][];
		visited[start[0]][start[1]] = true;
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			for(int[] d : dir) {
				int i = cur[0] + d[0];
				int j = cur[1] + d[1];
				if(!visited[i][j] && maze[i][j] == '.') {
					Q.add(new int[] {i, j});
					visited[i][j] = true;
					parent[i][j] = cur;
				}
			}
		}
		if(!visited[end[0]][end[1]]) return null;
		ArrayList<int[]> path = new ArrayList<>();
		int[] cur = end;
		while(parent[cur[0]][cur[1]] != null) {
			path.add(cur);
			cur = parent[cur[0]][cur[1]];
		}
		return path;
	}
	
}
