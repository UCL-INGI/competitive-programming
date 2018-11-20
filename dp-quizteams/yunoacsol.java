
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		n = reader.nextInt();
		x = new int[n];
		y = new int[n];
		for(int i = 0; i < n; i++) {
			x[i] = reader.nextInt();
			y[i] = reader.nextInt();
		}
		memo = new Double[1 << n];
		double ans = solve((1 << n) - 1);
		System.out.println(ans);
		reader.close();
	}
	
	static int[] x, y;
	static int n;
	static Double[] memo;
	
	static double solve(int s) {
		if(s == 0) return 0;
		if(memo[s] != null) return memo[s];
		double min = Double.POSITIVE_INFINITY;
		for(int i = 0; i < n; i++) {
			if(((s >> i) & 1) == 1) {
				for(int j = i + 1; j < n; j++) {
					if(((s >> j) & 1) == 1) {
						double val = dist(i, j) + solve((s ^ (1 << i)) ^ (1 << j));
						min = Math.min(min, val);
					}
				}
			}
		}
		memo[s] = min;
		return min;
	}
	
	static double dist(int i, int j) {
		double dx = x[i] - x[j];
		double dy = y[i] - y[j];
		return Math.sqrt(dx * dx + dy * dy);
	}
	
}
