import java.util.Scanner;

public class Knapsack {

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
		int[][] dp = new int[n][C + 1];
		// initialize the base cases
		for(int c = 0; c < C; c++) {
			dp[0][c] = c - w[0] >= 0 ? v[0] : 0;
		}
		// loop and apply recurrence
		for(int i = 1; i < n; i++) {
			for(int c = 0; c <= C; c++) {
				int take = c - w[i] >= 0 ? v[i] + dp[i - 1][c - w[i]] : 0;
				int skip = dp[i - 1][c];
				dp[i][c] = Math.max(take, skip);
			}
		}
		return dp[n - 1][C];
	}

}

