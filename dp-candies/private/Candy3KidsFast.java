
import java.util.Scanner;

public class Candy3KidsFast {
	
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
		memo = new Integer[n][S + 1][S + 1];
		int ans = solve(0, 0, 0);
		System.out.println(ans);
		reader.close();
	}
	
	static Integer[][][] memo;
	
	static int solve(int i, int v1, int v2) {
		if(i == n) {
			return max(v1, v2, S - v1 - v2) - min(v1, v2, S - v1 - v2);
		}
		if(memo[i][v1][v2] != null) {
			return memo[i][v1][v2];
		}
		int give1 = solve(i + 1, v1 + w[i], v2);
		int give2 = solve(i + 1, v1, v2 + w[i]);
		int give3 = solve(i + 1, v1, v2);
		memo[i][v1][v2] = min(give1, give2, give3);
		return memo[i][v1][v2];
	}
	
	static int max(int x, int y, int z) {
		return Math.max(Math.max(x, y), z);
	}
	
	static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

}
