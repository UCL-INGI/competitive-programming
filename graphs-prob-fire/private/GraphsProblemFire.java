import java.util.*;

public class GraphsProblemFire{
	@SuppressWarnings("unchecked") // not always necessary
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		char[][] maze = new char[n+2][m+2]; // +2 : borders
		for(int j = 0; j < m+2; j++){
			maze[0][j] = '#';
		}
		ArrayList<int[]> fireList = new ArrayList<>();
		String tokenLine = "";
		char token;
		int[] joe = new int[2];
		for(int i = 1; i < n+1; i++){
			tokenLine = reader.next();
			for(int j = 0; j < m+2; j++){
				if(j == 0 || j == m+1){
					maze[i][j] = '#';
					continue;
				}
				token = tokenLine.charAt(j-1);
				if(token == 'J'){
					joe[0] = i;
					joe[1] = j;
				}
				else if(token == 'F'){
					int[] fire = new int[] {i,j};
					fireList.add(fire);
				}
				maze[i][j] = token;
			}
		}
		for(int j = 0; j < m+2; j++){
			maze[n+1][j] = '#';
		}
		ArrayList<int[]> path = findPath(n,m,maze,joe,fireList);
		if(path == null)
			System.out.println("IMPOSSIBLE");
		else{
			System.out.print(path.size()+1);
		}
		reader.close();
	}
	
	static ArrayList<int[]> findPath(int n, int m, char[][] maze, int[] joe, ArrayList<int[]> fireList){
		
		// initialize the queues and visited matrix
		Queue<int[]> QJoe = new LinkedList<>();
		QJoe.add(joe);
		@SuppressWarnings("unchecked") // not always necessary
		Queue<int[]>[] QFire = new LinkedList[fireList.size()];
		boolean[][] visitedFire = new boolean[n+2][m+2]; // only by fire
		for(int i = 0; i < fireList.size(); i++){
			QFire[i] = new LinkedList<>();
			QFire[i].add(fireList.get(i));
			int[] index = fireList.get(i);
			visitedFire[index[0]][index[1]] = true;
		}
		// initialize the parent array (only joe parent)
		int[][][] parentJoe = new int[n+2][m+2][];
		
		boolean succeeded = false;
		boolean emptyQueues = false;
		int[] end = new int[2];
		//mazePrint(maze);
		while(!QJoe.isEmpty() && !succeeded) {
			for(int k = 0; k < QFire.length; k++){
				int sizeQFire = QFire[k].size();
				for(int l = 0; l < sizeQFire; l++){
					int[] u = QFire[k].poll();
					// we are now processing node u
					if(u == null){
						continue;
					}
					for(int[] d : dir) {
						int i = u[0] + d[0];
						int j = u[1] + d[1];
						// visit the edge from u to (i, j)
						if(!visitedFire[i][j] && maze[i][j] != '#') {
							maze[i][j] = 'V';
							// node (i, j) has not yet been visited and is not a wall, add it
							QFire[k].add(new int[] {i, j});
							visitedFire[i][j] = true;
						}
					}
				}
			}
			// we are now processing Joe
			int sizeQJoe = QJoe.size();
			for(int k = 0; k < sizeQJoe; k++){
				int[] u = QJoe.poll();
				// we are now processing node u
				if(u[0] == 1 || u[0] == maze[0].length-1 || u[1] == 0 || u[1] == maze.length-1){
					end[0] = u[0];
					end[1] = u[1];
					succeeded = true;
					continue;
				}
				for(int[] d : dir) {
					int i = u[0] + d[0];
					int j = u[1] + d[1];
					// visit the edge from u to (i, j)
					if(!visitedFire[i][j] && maze[i][j] == '.') {
						maze[i][j] = 'J';
						// node (i, j) has not yet been visited and is not a wall, add it
						QJoe.add(new int[] {i, j});
						// set the parent of (i, j) to be u
						parentJoe[i][j] = u;
					}
				}
			}
			//mazePrint(maze);
		}
		
		// check whether a path exists
		if(!succeeded) return null;
		// build the path by tracing back from t to s
		ArrayList<int[]> path = new ArrayList<>();
		int[] cur = end;
		// loop until we reach s (s is the only visited node with null parent)
		while(parentJoe[cur[0]][cur[1]] != null) {
			path.add(cur);
			cur = parentJoe[cur[0]][cur[1]];
		}
		// reverse and return the path
		// Collections.reverse(path);
		/* unnecessary here. Could even return an Integer : null if
		 * no result, or the length of the path if it exists.
		 */
		return path;
	}
	/*
	static void mazePrint(char[][] maze){
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[0].length; j++){
				System.out.print(maze[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.println("\nEnd of the array");
	}
	*/
}
