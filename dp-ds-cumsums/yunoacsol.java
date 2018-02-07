
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = reader.nextInt();
		}
		int[] s = new int[n];
		s[0] = a[0];
		for(int i = 1; i < n; i++) {
			s[i] = a[i] + s[i - 1];
		}
		int q = reader.nextInt();
		for(int i = 0; i < q; i++) {
			int i1 = reader.nextInt();
			int i2 = reader.nextInt();
			if(i1 == 0) {
				System.out.println(s[i2]);
			} else {
				System.out.println(s[i2] - s[i1 - 1]);
			}
		}
		reader.close();
	}

}
