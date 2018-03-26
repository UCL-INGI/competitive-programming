
import java.util.Scanner;

public class KnapsackCondensed3 {

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
			for(int c = C; c >= w[i]; c--) { // loop in decreasing order
				dp[c] = Math.max(dp[c], v[i] + dp[c - w[i]]);
			}
		}
		return dp[C];
	}


}
