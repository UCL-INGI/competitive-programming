
import java.util.Arrays;
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int cap = reader.nextInt();
		int n = reader.nextInt();
		int[] weights = new int[n];
		int[] values = new int[n];
		for(int i = 0; i < n; i++) {
			weights[i] = reader.nextInt();
			values[i] = reader.nextInt();
		}
		int ans = knapsack(cap, n, weights, values);
		System.out.println(ans);
		reader.close();
	}
	
	static int knapsack(int C, int n, int[] w, int [] v) {
		  int[] dp = new int[C + 1];
		  // initialize the base cases
		  for(int c = 0; c < C; c++) {
		    dp[c] = c - w[0] >= 0 ? v[0] : 0;
		  }
		  // loop and apply recurrence
		  int[] tmp = new int[C + 1];
		  for(int i = 1; i < n; i++) {
		    for(int c = 0; c <= C; c++) {
		      int take = c - w[i] >= 0 ? v[i] + dp[c - w[i]] : Integer.MIN_VALUE;
		      int skip = dp[c];
		      tmp[c] = Math.max(take, skip);
		    }
		    dp = Arrays.copyOfRange(tmp, 0, tmp.length);
		  }
		  return dp[C];
		}
	
}
