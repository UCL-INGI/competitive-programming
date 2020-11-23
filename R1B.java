import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class R1B {
	
/*
9
6008 1300
6000 2100
500 2000
1000 4000
1100 3000
6000 2000
8000 1400
6000 1200
2000 1900

 */
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		ArrayList<E> e = new ArrayList<>();
		int index = 1;
		for(int i = 0; i < n; i++) {
			int size = reader.nextInt();
			int iq = reader.nextInt();
			e.add(new E(size, iq, index++));
		}
		reader.close();
		Collections.sort(e);
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			int max = 0;
			for(int j = 0; j < i; j++) {
				if(e.get(j).size < e.get(i).size && e.get(j).iq > e.get(i).iq && dp[j] > max) {
					max = dp[j];
				}
			}
			dp[i] = 1 + max;
		}
		int max = -1;
		for(int i = 0; i < n; i++) {
			if(dp[i] > max) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
	
	static class E implements Comparable<E> {
		
		int size, iq, index;
		
		public E(int size, int iq, int index) {
			this.size = size;
			this.iq = iq;
			this.index = index;
		}

		public int compareTo(E o) {
			if(size == o.size) {
				return -(iq - o.iq);
			}
			return size - o.size;
		}
		
		public String toString() {
			return String.format(String.format("(%d, %d)", size, iq));
		}
	}

}
