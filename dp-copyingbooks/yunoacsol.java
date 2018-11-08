
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int p = reader.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = reader.nextInt();
		}
		int ans = solve(a, p);
		System.out.println(ans);
		reader.close();
	}

	static int solve(int[] a, int p) {
		int[][] dp = new int[p + 1][a.length];
		// solve the base case
		dp[1][0] = a[0];
		for(int i = 1; i < a.length; i++) {
			dp[1][i] = dp[1][i - 1] + a[i];
		}
		// solve general case
		for(int j = 2; j <= p; j++) {
			for(int i = a.length - 1; i >= 0; i--) {
				// loop over choices, one person does a[0]...a[k]
				// and then the j-1 persons do a[k+1]...a[n-1] optimally
				int sum = 0;
				int min = Integer.MAX_VALUE;
				for(int k = i; k >= 0; k--) {
					sum += a[k];
					int left = k == 0 ? 0 : dp[j - 1][k - 1];
					min = Math.min(min, Math.max(sum, left));
				}
				dp[j][i] = min;
			}
		}
		return dp[p][a.length - 1];
	}

}

