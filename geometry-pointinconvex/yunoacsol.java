
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		Point[] pol = new Point[n];
		for(int i = 0; i < n; i++) {
			pol[i] = new Point(reader.nextInt(), reader.nextInt());
		}
		int q = reader.nextInt();
		for(int i = 0; i < q; i++) {
			int x = reader.nextInt();
			int y = reader.nextInt();
			Point p = new Point(x, y);
			boolean hasPos = false;
			boolean hasNeg = false;
			boolean hasZero = false;
			for(int j = 0; j < n; j++) {
				double o = orient(p, pol[j], pol[(j + 1) % n]);
				if(o < 0) hasPos = true;
				if(o > 0) hasNeg = true;
				if(o == 0) hasZero = true;
			}
			if(hasNeg && hasPos) {
				System.out.println("outside");
			} else if(!hasZero) {
				System.out.println("inside");
			} else {
				System.out.println("boundary");
			}
		}
		reader.close();
	}
	
	static class Point {
		
		double x, y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	// create the vector from point a to point b
	static Point vec(Point a, Point b) {
	    return new Point(b.x - a.x, b.y - a.y);
	}

	static double orient(Point a, Point b, Point c) {
	    return cross(vec(a, b), vec(a, c));
	}
	
	static double cross(Point v, Point w) {
	    return v.x * w.y - v.y * w.x;
	}
	
	static double sorient(Point a, Point b, Point c) {
	    double o = orient(a, b, c);
	    return o < 0 ? -1 : o > 0 ? 1 : 0;
	}
	
}
