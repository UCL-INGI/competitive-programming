
import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int Ax = reader.nextInt();
		int Ay = reader.nextInt();
		int Bx = reader.nextInt();
		int By = reader.nextInt();
		int Cx = reader.nextInt();
		int Cy = reader.nextInt();
		int Px = reader.nextInt();
		int Py = reader.nextInt();
		Point a = new Point(Ax, Ay);
		Point b = new Point(Bx, By);
		Point c = new Point(Cx, Cy);
		Point p = new Point(Px, Py);
		
		if(inAngleStrict(a, b, c, p)) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		reader.close();
	}
	
	static boolean inAngleStrict(Point a, Point b, Point c, Point p) {
		if(orient(a, b, c) < 0) return inAngleStrict(a, c, b, p);
		return orient(a, b, p) > 0 && orient(a, c, p) < 0;
	}
	
	static double orient(Point a, Point b, Point c) {
		return cross(vec(a, b), vec(a, c));
	}
	
	static Point vec(Point a, Point b) {
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	static double cross(Point v, Point w) {
		return v.x * w.y - v.y * w.x;
	}
	
	static class Point {
		
		double x ,y;
		
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

	}

	
}
