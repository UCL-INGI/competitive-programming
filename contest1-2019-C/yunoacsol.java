import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class R1C {
	
/*

5 9
****#****
*..#.#..*
****.****
*$#.#.#$*
*********

5 11
*#*********
*$*...*...*
*$*.*.*.*.*
*...*...*.*
*********.*

9 9
*#**#**#*
*#**#**#*
*#**#**#*
*#**.**#*
*#*#.#*#*
*$##*##$*
*#*****#*
*.#.#.#.*
*********	

4 4
.*#.
*$.*
*.$*
.**.

3 3
***
$*$
***

3 5
*****
#$*$#
*****

3 5
***.*
#$*$#
*.***


 */

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		char[][] grid = new char[n][];
		for(int i = 0; i < n; i++) {
			grid[i] = reader.next().toCharArray();
		}
		reader.close();
		double[][] d1 = null;
		double[][] d2 = null;
		ArrayList<int[]> border = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(grid[i][j] == 'P') {
					if(d1 == null) {
						d1 = bfs(i, j, grid);
					} else {
						d2 = bfs(i, j, grid);
					}
				}
				if(i == 0 || i == n - 1 || j == 0 || j == m -1) {
					border.add(new int[] {i, j});
				}
			}
		}
		double[][] d3 = bfs(border, grid);
		double min = Double.POSITIVE_INFINITY;
		for(int[] b1 : border) {
			for(int[] b2 : border) {
				int i1 = b1[0];
				int j1 = b1[1];
				int i2 = b2[0];
				int j2 = b2[1];
				if(i1 == i2 && j1 == j2) continue;
				min = Math.min(min, d1[i1][j1] + d2[i2][j2]);
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				double doors = d1[i][j] + d2[i][j] + d3[i][j];
				if(grid[i][j] == '@') {
					doors -= 2;
				}
				if(doors < min) {
					min = doors;
				}
			}
		}
		System.out.println((int)min);
	}
	
	static int[][] dir = new int[][] {
		new int[] {-1, 0},
		new int[] {1, 0},
		new int[] {0, -1},
		new int[] {0, 1}
	};
	
	static double[][] bfs(int r, int c, char[][] grid) {
		ArrayList<int[]> S = new ArrayList<>();
		S.add(new int[] {r, c});
		return bfs(S, grid);
	}
	
	static double[][] bfs(ArrayList<int[]> S, char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		double[][] dist = new double[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
		}
		LinkedList<int[]> Q = new LinkedList<>();
		for(int[] s : S) {
			if(grid[s[0]][s[1]] == '@') {
				dist[s[0]][s[1]] = 1;				
				Q.addLast(s);			
			} else if(grid[s[0]][s[1]] != '#') {
				dist[s[0]][s[1]] = 0;				
				Q.addFirst(s);	
			}
		}
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			for(int[] d : dir) {
				int i = cur[0] + d[0];
				int j = cur[1] + d[1];
				if(0 <= i && i < n && 0 <= j && j < m && grid[i][j] != '#' && dist[i][j] == Double.POSITIVE_INFINITY) {
					if(grid[i][j] == '@') {
						dist[i][j] = 1 + dist[cur[0]][cur[1]];
						Q.addLast(new int[] {i, j});
					} else  {
						dist[i][j] = dist[cur[0]][cur[1]];
						Q.addFirst(new int[] {i, j});
					}
				}
			}
		}
		return dist;
	}
	
}
