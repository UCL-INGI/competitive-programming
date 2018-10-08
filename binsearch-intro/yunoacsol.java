import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int q = reader.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = reader.nextInt();
		}
		for(int i = 0; i < q; i++) {
			int t = reader.nextInt();
			int idx = binSearchMinIndex(a, t);
			System.out.println(idx);
		}
		reader.close();
	}
	
	static int binSearchMinIndex(int[] a, int t) {
		int L = 0;
		int R = a.length - 1;
		while(L <= R) {
			if(L == R) return a[L] == t ? L : -1;
			int M = (L + R) / 2;
			if(a[M] < t) L = M + 1;
			else if(a[M] > t) R = M - 1;
			else R = M;
		}
		return -1;
	}

}

