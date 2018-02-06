
import java.util.Arrays;
import java.util.Scanner;

public class KnapsackNVCondensed2 {
	
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
		double[] dp = new double[vsum + 1];
		Arrays.fill(dp, Double.POSITIVE_INFINITY);
		dp[0] = 0;
		dp[V[0]] = W[0];
		double[] tmp = new double[vsum + 1];
		for(int i = 1; i < N; i++) {
			for(int v = 0; v <= vsum; v++) {
				tmp[v] = dp[v];
				if(v - V[i] >= 0 && dp[v - V[i]] + W[i] < tmp[v]) {
					tmp[v] = dp[v - V[i]] + W[i];
				}
			}
			dp = Arrays.copyOf(tmp, tmp.length);
		}
		int v = vsum;
		while(v >= 0 && dp[v] > C) {
			v--;
		}
		System.out.println(v);
	}

}
