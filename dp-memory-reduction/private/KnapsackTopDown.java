
import java.util.Scanner;

public class KnapsackTopDown {

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
		memo = new Double[n][C + 1];
		int ans = (int)dp(0, C);
		System.out.println(ans);
		reader.close();
	}
	
	static int C, n;
	static int[] w, v;
	static Double[][] memo;

	static double dp(int i, int c) {
	  if(c < 0) return Double.NEGATIVE_INFINITY;
	  if(i == n) return 0;
	  // check if the value for state (i, c) has already been computed
	  if(memo[i][c] != null) return memo[i][c];
	  // do not take item i
	  double skip = dp(i + 1, c);
	  // take item i
	  double take = v[i] + dp(i + 1, c - w[i]);
	  // memorize the value of state (i, c)
	  memo[i][c] = Math.max(skip, take);
	  return memo[i][c];
	}
	
}

