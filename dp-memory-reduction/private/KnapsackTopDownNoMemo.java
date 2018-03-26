
import java.util.Scanner;

public class KnapsackTopDownNoMemo {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		C = reader.nextInt();
		n = reader.nextInt();
		w = new int[n];
		v = new int[n];
		for(int i = 0; i < n; i++) {
			w[i] = reader.nextInt();
			v[i] = reader.nextInt();
		}
		int ans = (int)dp(0, C);
		System.out.println(ans);
		reader.close();
	}
	
	static int C, n;
	static int[] w, v;

	static double dp(int i, int c) {
      if(c < 0) return Double.NEGATIVE_INFINITY;
	  if(i == n) return 0;
	  // do not take item i
	  double skip = dp(i + 1, c);
	  // take item i
	  double take = v[i] + dp(i + 1, c - w[i]);
	  // memorize the value of state (i, c)
	  return Math.max(skip, take);
	}
	
}
