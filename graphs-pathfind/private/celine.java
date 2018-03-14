import java.util.*;
public class celine
{
     
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
        reader.close();
        ArrayList<Integer> sol = findPath(g, s, t);
        if(sol.size() == 0){
             System.out.println("impossible");
             return;
        }
				String ans = "";
        for(Integer el : sol){
            ans += el + " ";
        }
				ans = ans.trim();
        System.out.println(s);
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
        // in practice you might want to stop as soon as end is visited
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
