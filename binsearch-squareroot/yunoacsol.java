import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		reader.close();
		int s = integerSqrt(n);
		System.out.println(s * s == n ? "yes" : "no");
	}
	
	static int integerSqrt(int n) {
		long L = 0;
		long R = n + 1;
		while(R - L >= 2) {
			long M = (L + R) / 2;
			if(M * M <= n) L = M;
			else R = M;
		}
		return (int)L;
	}

}
