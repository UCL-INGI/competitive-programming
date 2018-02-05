
import java.util.Scanner;

public class KnapsackCondensed2 {

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
		for(int i = 0; i < n; i++) {
			for(int c = C; c >= 0; c--) { // loop in decreasing order
				int take = c - w[i] >= 0 ? v[i] + dp[c - w[i]] : Integer.MIN_VALUE;
				int skip = dp[c];
				dp[c] = Math.max(take, skip);
			}
		}
		return dp[C];
	}


}
