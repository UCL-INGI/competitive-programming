import java.util.Scanner;

public class Candy3Kids {
	
	static int n, S;
	static int[] w;
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		n = reader.nextInt();
		w = new int[n];
		S = 0;
		for(int i = 0; i < n; i++) {
			w[i] = reader.nextInt();
			S += w[i];
		}
		memo = new Integer[n][S + 1][S + 1][S + 1];
		int ans = dp(0, 0, 0, 0);
		System.out.println(ans);
		reader.close();
	}
	
	static Integer[][][][] memo;
	
	static int dp(int i, int v1, int v2, int v3) {
		if(i == n) {
			return max(v1, v2, v3) - min(v1, v2, v3);
		}
		if(memo[i][v1][v2][v3] != null) {
			return memo[i][v1][v2][v3];
		}
		int give1 = dp(i + 1, v1 + w[i], v2, v3);
		int give2 = dp(i + 1, v1, v2 + w[i], v3);
		int give3 = dp(i + 1, v1, v2, v3 + w[i]);
		memo[i][v1][v2][v3] = min(give1, give2, give3);
		return memo[i][v1][v2][v3];
	}
	
	static int max(int x, int y, int z) {
		return Math.max(Math.max(x, y), z);
	}
	
	static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

}
