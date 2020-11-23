import java.util.Scanner;
import java.lang.Math.*;
public class yunoacsol{
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		double theta = sc.nextDouble();
		double angle = theta*Math.PI/180.0;
		double cx = sc.nextDouble();
		double cy = sc.nextDouble();
		for(int i = 0; i < N; i++){
			double x,y;
			x = sc.nextDouble(); y = sc.nextDouble();
			double x2 = cx + (x-cx)*Math.cos(angle) - (y-cy)*Math.sin(angle);
			double y2 = cy + (x-cx)*Math.sin(angle) + (y-cy)*Math.cos(angle);
			System.out.println(String.format("%f %f",x2,y2));
		}
	}
}
