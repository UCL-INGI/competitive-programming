
import java.util.Arrays;
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int C = reader.nextInt();
		int N = reader.nextInt();
		int[] W = new int[N];
		int[] V = new int[N];
		int vsum = 0;
		for(int i = 0; i < N; i++) {
			W[i] = reader.nextInt();
			V[i] = reader.nextInt();
			vsum += V[i];
		}
		reader.close();
		double[][] dp = new double[N][vsum + 1];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Double.POSITIVE_INFINITY);
		}
		dp[0][0] = 0;
		dp[0][V[0]] = W[0];
		for(int i = 1; i < N; i++) {
			for(int v = 0; v <= vsum; v++) {
				dp[i][v] = dp[i - 1][v];
				if(v - V[i] >= 0 && dp[i - 1][v - V[i]] + W[i] < dp[i][v]) {
					dp[i][v] = dp[i - 1][v - V[i]] + W[i];
				}
			}
		}
		int v = vsum;
		while(v >= 0 && dp[N - 1][v] > C) {
			v--;
		}
		System.out.println(v);
	}

}
