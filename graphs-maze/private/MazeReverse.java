package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeReverse {

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

9 9
#########
#......S#
#.#.#####
#...#...#
#.#####.#
#.......#
#.#####.#
#.#T....#
#########

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
maze[pos[0]][pos[1]] = 'x';
}
/*
for(int i = 0; i < n; i++) {
for(int j = 0; j < m; j++) {
System.out.print(maze[i][j]);
}
System.out.println();
}
*/
reader.close();
}

static int[][] dir = new int[][] {
new int[] {-1, 0},
new int[] {1, 0},
new int[] {0, -1},
new int[] {0, 1}
};

static ArrayList<int[]> findPath(int n, int m, char[][] maze, int[] start, int[] end) {
// initialize the queue and visited matrix
Queue<int[]> Q = new LinkedList<>();
Q.add(start);
boolean[][] visited = new boolean[n][m];
// initialize the parent array
int[][][] parent = new int[n][m][];
visited[start[0]][start[1]] = true;
while(!Q.isEmpty()) {
int[] u = Q.poll();
// we are now processing node u
for(int[] d : dir) {
int i = u[0] + d[0];
int j = u[1] + d[1];
// visit the edge from u to (i, j)
if(!visited[i][j] && maze[i][j] == '.') {
// node (i, j) has not yet been visited and is not a wall, add it
Q.add(new int[] {i, j});
visited[i][j] = true;
// set the parent of (i, j) to be u
parent[i][j] = u;
}
}
}
// check whether a path exists
if(!visited[end[0]][end[1]]) return null;
// build the path by tracing back from t to s
ArrayList<int[]> path = new ArrayList<>();
int[] cur = end;
// loop until we reach s (s is the only visited node with null parent)
while(cur != null) {
path.add(cur);
cur = parent[cur[0]][cur[1]];
}
// reverse and return the path
return path;
}

}
