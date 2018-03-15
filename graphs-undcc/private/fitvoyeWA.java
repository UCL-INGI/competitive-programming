import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Scanner;




class fitvoye {

  static int nC = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    @SuppressWarnings("unchecked")
    LinkedList<Integer>[] g = new LinkedList[n];
    for(int i = 0; i < n; i++){
      g[i] = new LinkedList<>();
    }
    sc.nextLine();
    for(int i = 0; i < m; i++){
      int u = sc.nextInt();
      int v = sc.nextInt();
      sc.nextLine();
      g[u].add(v);
      g[v].add(u);
    }

    Integer[] conn = connectedComponents(g);

    int q = sc.nextInt();
    sc.nextLine();
    System.out.println(nC);
    for (int i = 0; i < q; i++){
      int a = sc.nextInt();
      int b = sc.nextInt();
      sc.nextLine();
      if(conn[a] == conn[b])
        System.out.println("yes");
      else
        System.out.println("no");
    }
    sc.close();
  }

  static Integer[] connectedComponents(LinkedList<Integer>[] g){
    int compId = 0;
    Integer [] comp = new Integer[g.length];
    for(int i = 0; i < g.length; i++){
      if(comp[i] != null)
        continue;
      Queue<Integer> q = new LinkedList<>();
      q.add(i);
      comp[i] = compId;
      while(!q.isEmpty()){
        int u = q.poll();
        for (int v : g[u]){
          if (comp[v] == null){
            q.add(v);
            comp[v] = compId;
          }
        }
      }
      compId++;
    }
    nC = compId;
    return comp;
  }
}

