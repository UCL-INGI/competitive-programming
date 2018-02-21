
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class yunoacsol {
	
/*
14 15
0 1
0 2
0 3
0 4
1 5
1 6
2 7
6 7
5 4
4 8
8 9
9 3
3 10
10 11
12 13
0 11

14 15
0 1
0 2
0 3
0 4
1 5
1 6
2 7
6 7
5 4
4 8
8 9
9 3
3 10
10 11
12 13
0 0


 */

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] g = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			g[i] = new LinkedList<>();
		}
		for(int i = 0; i < m; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			g[u].add(v);
		}
		int s = reader.nextInt();
		int t = reader.nextInt();
		ArrayList<Integer> path = findPath(g, s, t);
		if(path == null) {
			System.out.println("impossible");
		} else {
			String ans = path.toString();
			ans = ans.substring(1, ans.length() - 1);
			ans = ans.replace(", ", " ");
			System.out.println(ans);
		}
		reader.close();
	}
	
	static boolean pathExists(LinkedList<Integer>[] g, int s, int t) {
        // initialize the queue and visited set
		Queue<Integer> Q = new LinkedList<>();
		Q.add(s);
        BitSet visited = new BitSet();
		visited.set(s);
		// loop while there are nodes in the queue to process
		while(!Q.isEmpty()) {
			int u = Q.poll();
			// we are now processing node u
			for(int v : g[u]) {
				// visit edge (u, v)
				if(!visited.get(v)) {
					// node v has not yet been visited, add it
					Q.add(v);
					visited.set(v);
				}
			}
		}
		// return whether a path exists
		return visited.get(t);
	}
	
	static ArrayList<Integer> findPath(LinkedList<Integer>[] g, int s, int t) {
        // initialize the queue and visited set
        Queue<Integer> Q = new LinkedList<>();
        Q.add(s);
        BitSet visited = new BitSet();
        visited.set(s);
        // initialize the parent array
        Integer[] parent = new Integer[g.length];
        // loop while there are nodes in the queue to process
        while(!Q.isEmpty()) {
            int u = Q.poll();
            // we are now processing node u
            for(int v : g[u]) {
                // visit edge (u, v)
                if(!visited.get(v)) {
                    // node v has not yet been visited, add it
                    Q.add(v);
                    visited.set(v);
                    // set the parent of v to be u
                    parent[v] = u;
                }
            }
        }
        // check whether a path exists
        if(!visited.get(t)) return null;
        // build the path by tracing back from t to s
        ArrayList<Integer> path = new ArrayList<>();
        Integer cur = t;
        // loop until we reach s (s is the only visited node with null parent)
        while(cur != null) {
        	path.add(cur);
        	cur = parent[cur];
        }
        // return the path
        Collections.reverse(path);
        return path;
    }
	

}
