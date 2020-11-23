package student;

import java.util.*;

public class Geometry_Problem_AlignPolygon {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 1){
            System.out.println("0 0");
            return;
        }
        double[] x = new double[n];
        double[] y = new double[n];
        for(int i = 0; i < n; i++){
            x[i] = sc.nextDouble();
            y[i] = sc.nextDouble();
        }
        for(int i = 1; i < n; i++){ // translate
            x[i] -= x[0];
            y[i] -= y[0];
        }
        double theta = Math.atan2(x[1],y[1]);
        if(y[1] == 0) theta = 0;
        double[] newx = new double[n];
        double[] newy = new double[n];
        newx[0] = 0; newy[0] = 0;
        System.out.println(newx[0]+" "+newy[0]);
        for(int i = 1; i < n; i++) {
            newx[i] = x[i] * Math.cos(theta) + y[i] * Math.sin(theta); // not the same formula because theta is taken in opposite direction
            newy[i] = - x[i] * Math.sin(theta) + y[i] * Math.cos(theta);
            if(Math.abs(newx[i]) < 0.00000001) newx[i] = 0;
            if(Math.abs(newy[i]) < 0.00000001) newy[i] = 0;
            System.out.println(newx[i]+" "+newy[i]);
        }
    }
}