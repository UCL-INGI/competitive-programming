
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		int[][] A = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				A[i][j] = reader.nextInt();
			}
		}
		long[][] s = new long[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				s[i][j] += A[i][j];
				if(i > 0) s[i][j] += A[i - 1][j];
				if(j > 0) s[i][j] += A[i][j - 1];
				if(i > 0 && j > 0) s[i][j] -= A[i - 1][j - 1];
			}
		}
		int q = reader.nextInt();
		for(int i = 0; i < q; i++) {
			int i1 = reader.nextInt();
			int j1 = reader.nextInt();
			int i2 = reader.nextInt();
			int j2 = reader.nextInt();
			long ans = s[i2][j2];
			if(i1 > 0) ans -= s[i1 - 1][j2];
			if(j1 > 0) ans -= s[i2][j1 - 1];
			if(i1 > 0 && j1 > 0) ans += s[i1 - 1][j1 - 1];
			System.out.println(ans);
		}
		reader.close();
	}
	
}
