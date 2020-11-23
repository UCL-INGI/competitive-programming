import java.util.ArrayList;
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		Polygon P = new Polygon();
		for(int i = 0; i < n; i++) {
			P.add(new Point(reader.nextInt(), reader.nextInt()));
		}
		P = alignX(P);
		Point c = middle(P.get(0), P.get(1));
		Line l1 = new Line(c, translate(c, 1, 1));
		Line l2 = new Line(c, translate(c, -1, 1));
		Point p1 = null;
		int i1 = -1;
		for(int i = 1; i < P.size(); i++) {
			Segment s = new Segment(P.get(i), P.get(i + 1));
			p1 = intersect(l1, s);
			if(p1 != null) {
				i1 = i + 1;
				break;
			}
		}
		Point p2 = null;
		int i2 = -1;
		for(int i = 1; i < P.size(); i++) {
			Segment s = new Segment(P.get(i), P.get(i + 1));
			p2 = intersect(l2, s);
			if(p2 != null) {
				i2 = i;
				break;
			}
		}
		Polygon camera = new Polygon();
		camera.add(c);
		camera.add(p1);
		for(int i = i1; i <= i2; i++) {
			camera.add(P.get(i));
		}
		camera.add(p2);
		
		double areaCamera = areaPolygon(camera);
		double areaTotal = areaPolygon(P);
		
		System.out.println(areaCamera / areaTotal);
		reader.close();
	}
	
	// GEOMETRY
	
	static double eps = 1e-6;
	
	static Polygon alignX(Polygon P) {
		// 1. put P[0] in the origin
		int n = P.size();
		Polygon Q = new Polygon();
		for(int i = 0; i < n; i++) {
			Q.add(translate(P.get(i), -P.get(0).x, -P.get(0).y));
		}
		// 2. rotate so that P[0]P[1] is aligned with the x-axis and P[1].x > 0
		Point v = vector(Q.get(0), Q.get(1));
		Point u = new Point(1, 0);
		double a = angle(v, u);
		if(Q.get(1).y < 0) a = -a;
		for(int i = 0; i < n; i++) {
			Q.set(i, rotate(Q.get(i), -a));
		}
		return Q;
	}
	
	static Point vector(Point a, Point b) {
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	static double angle(Point v, Point u) {
		return Math.acos(dot(v, u) / abs(v) / abs(u));
	}
	
	static double dot(Point v, Point u) {
		return v.x * u.x + v.y * u.y;
	}
	
	static double cross(Point v, Point u) {
		return v.x * u.y - v.y * u.x;
	}
	
	static double abs(Point v) {
		return Math.sqrt(v.x * v.x + v.y * v.y);
	}
	
	static Point middle(Point a, Point b) {
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}
	
	static Point add(Point a, Point b) {
		return new Point(a.x + b.x, a.y + b.y);
	}
	
	static Point translate(Point p, double dx, double dy) {
		return new Point(p.x + dx, p.y + dy);
	}
	
	static Point rotate(Point p, double a) {
		return new Point(p.x * Math.cos(a) - p.y * Math.sin(a), p.x * Math.sin(a) + p.y * Math.cos(a));
	}	

	static Point intersect(Line l1, Line l2) {
		double d = cross(l1.v, l2.v);
		if(eq(d, 0)) return null;
		double x = (l2.v.x * l1.c - l1.v.x * l2.c) / d;
		double y = (l2.v.y * l1.c - l1.v.y * l2.c) / d;
		return new Point(x, y);
	}
	
	static Point intersect(Line l, Segment s) {
		Line ls = new Line(s.a, s.b);
		Point p = intersect(l, ls);
		if(p == null) return null;
		return onSegment(s, p) ? p : null;
	}
	
	static double orient(Point a, Point b, Point c) {
		Point ab = vector(a, b);
		Point ac = vector(a, c);
		return cross(ab, ac);
	}
	
	static boolean inDisk(Segment s, Point p) {
		Point v = vector(p, s.a);
		Point u = vector(p, s.b);
		return lessEq(dot(u, v), 0);
	}
	
	static boolean onSegment(Segment s, Point p) {
		double o = orient(s.a, s.b, p);
		return eq(o, 0) && inDisk(s, p);
	}
	
	static double areaPolygon(Polygon P) {
		double area = 0;
		for(int i = 0; i < P.size(); i++) {
			area += cross(P.get(i), P.get(i + 1));
		}
		return area / 2.0;
	}
	
	static class Point {
		
		double x, y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return String.format("(%.3f, %.3f)", x, y);
		}
		
	}
	
	static class Line {
		
		Point v;
		double c;
		
		public Line(Point v, double c) {
			this.v = v;
			this.c = c;
		}
		
		public Line(Point p, Point q) {
			v = vector(p, q);
			c = cross(v, p);
		}
		
		public Line(double a, double b, double c) {
			v = new Point(b, -a);
			this.c = c;
		}
		
	}
	
	static class Segment {
		
		Point a, b;
		
		public Segment(Point p, Point q) {
			this.a = p;
			this.b = q;
		}
		
	}
	
	static class Polygon {
		
		ArrayList<Point> P;
		
		public Polygon() {
			P = new ArrayList<>();
		}
		
		public Polygon(ArrayList<Point> P) {
			this.P = P;
		}
		
		public Polygon(Point[] P) {
			this.P = new ArrayList<>();
			for(int i = 0; i < P.length; i++) {
				this.P.add(P[i]);
			}
		}
		
		public void set(int i, Point p) {
			P.set(i, p);
		}
		
		public int size() {
			return P.size();
		}
		
		public void add(Point p) {
			P.add(p);
		}
		
		public Point get(int i) {
			return P.get(i % size());
		}
		
		public String toString() {
			return P.toString();
		}
	}
	
	static boolean eq(double a, double b) {
		return Math.abs(a - b) <= eps;
	}
	
	static boolean lessEq(double a, double b) {
		return a <= b + eps;
	}
	
}
