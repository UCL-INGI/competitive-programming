import java.util.Scanner;

public class student {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        sc.nextLine();
        int [] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = sc.nextInt();
        }
        //sc.nextLine();
        for (int i =0; i < q; i++){
            int t = sc.nextInt();
            //System.out.println("t " + t);
            sc.nextLine();
            System.out.println(binarySearch(tab, t));
        }
        sc.close();
    }

    static int binarySearch(int[]a, int t){
        int left = 0;
        int right = a.length-1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (a[mid]==t){
                while(mid >= 0 && a[mid] == t){
                    mid--;
                }
                return (mid+1);
            } else if (a[mid] < t)
                left = mid +1;
            else
                right = mid -1;
        }
        return -1;
    }

}

