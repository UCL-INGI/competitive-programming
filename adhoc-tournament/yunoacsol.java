
import java.util.Scanner;

public class yunoacsol {
	
/*
8
3
1 1
1 2
1 3

4
8
1 1
1 2
2 1
2 2
3 1
3 2
4 1
4 2

8
24
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
3 3
4 1
4 2
4 3
5 1
5 2
5 3
6 1
6 2
6 3
7 1
7 2
7 3
8 1
8 2
8 3

 */
	
	static int[][][] buildA(int n) {
		int k = (int)(Math.log(n) / Math.log(2));
		int[][][] A = new int[k][n][2];
		for(int i = 0; i < n; i++) {
			buildA(A, k - 1, i, 0, n - 1);		
		}
		return A;
	}

	static void buildA(int[][][] A, int k, int i, int L, int R) {
		if(k < 0) return;
		int M = (L + R) / 2;
		if(L <= i && i <= M) {
			// go left
			A[k][i][0] = M + 1;
			A[k][i][1] = R;
			buildA(A, k - 1, i, L, M);
		} else {
			// go right
			A[k][i][0] = L;
			A[k][i][1] = M;
			buildA(A, k - 1, i, M + 1, R);
		}
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[][][] A = buildA(n);
		int q = reader.nextInt();
		for(int i = 0; i < q; i++) {
			int p = reader.nextInt() - 1;
			int r = reader.nextInt() - 1;
			int a = A[r][p][0] + 1;
			int b = A[r][p][1] + 1;
			System.out.println(a + " " + b);
		}
		reader.close();
	}

}
