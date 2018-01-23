
import java.util.LinkedList;
import java.util.Scanner;

public class wa1 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		C = reader.nextInt();
		n = reader.nextInt();
		w = new int[n];
		v = new int[n];
		for(int i = 0; i < n; i++) {
			w[i] = reader.nextInt();
			v[i] = reader.nextInt();
		}
		memo = new Double[n][C + 1];
		action = new Integer[n][C + 1];
		int ans = (int)dp(0, C);
		System.out.println(ans + 1);
		LinkedList<Integer> sol = buildSolution();
		String out = sol.toString();
		out = out.substring(1, out.length() - 1);
		out = out.replace(", ", " ");
		System.out.println(out);
		reader.close();
	}

	static int C, n;
	static int[] w, v;
	static Double[][] memo;
	static int SKIP = 0, TAKE = 1;
	static Integer[][] action;

	static double dp(int i, int c) {
		if(c < 0) return Double.NEGATIVE_INFINITY;
		if(i == n) return 0;
		// check if the value for state (i, c) has already been computed
		if(memo[i][c] != null) return memo[i][c];
		// do not take item i
		double skip = dp(i + 1, c);
		// take item i
		double take = v[i] + dp(i + 1, c - w[i]);
		// memorize the value of state (i, c) and set action
		if(skip >= take) {
			memo[i][c] = skip;
			action[i][c] = SKIP;
		} else {
			memo[i][c] = take;
			action[i][c] = TAKE;
		}
		return memo[i][c];
	}

	static LinkedList<Integer> buildSolution() {
		int i = 0;
		int c = C;
		LinkedList<Integer> taken = new LinkedList<>();
		while(i < n && c >= 0) {
			if(action[i][c] == TAKE) {
				taken.add(i);
				c -= w[i];
			}
			i += 1;
		}
		return taken;
	}

}
