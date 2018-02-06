
import java.util.Arrays;
import java.util.Scanner;

public class Candy3KidsGreedyDecreasing {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[] v = new int[n];
		for(int i = 0; i < n; i++) {
			v[i] = reader.nextInt();
		}
		Arrays.sort(v);
		Kid[] kids = new Kid[3];
		for(int i = 0; i < kids.length; i++) {
			kids[i] = new Kid(0, i);
		}
		int j = n - 1;
		while(j >= 0) {
			Arrays.sort(kids);
			kids[0].v += v[j--];
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < kids.length; i++) {
			max = Math.max(max, kids[i].v);
			min = Math.min(min, kids[i].v);
		}
		System.out.println(max - min);
		reader.close();
	}
	
	static class Kid implements Comparable<Kid> {
		
		int v, index;
		
		public Kid(int v, int index) {
			this.v = v;
			this.index = index;
		}

		public int compareTo(Kid o) {
			return v - o.v;
		}
		
	}

}
