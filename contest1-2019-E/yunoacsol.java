import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int k = reader.nextInt();
		int d = reader.nextInt();
		int[] x = new int[k];
		int[] f = new int[k];
		for(int i = 0; i < k; i++) {
			x[i] = reader.nextInt();
			f[i] = reader.nextInt();
		}
		double lb = 0;
		double ub = 1e12;
		canDo(2, d, x, f);
		for(int i = 0; i < 200; i++) {
			double t = (lb + ub) / 2;
			if(canDo(t, d, x, f)) {
				ub = t;
			} else {
				lb = t;
			}
		}
		System.out.println(lb);
		reader.close();
	}

	static boolean canDo(double t, double d, int[] x, int[] f) {
		double maxLeft = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < x.length; i++) {
			double left = Math.max(maxLeft + d, x[i] - t);
			double right = x[i] + t;
			if(right - left < d * (f[i] - 1)) return false;
			maxLeft = left + d * (f[i] - 1);
		}
		return true;
	}

}
