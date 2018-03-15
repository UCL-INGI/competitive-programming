import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/*
2 2
10
01

2 2
10
11

2 2
00
00



 */

public class yunoacsol {

	public static void main(String[] args) throws IOException {
		
		solve(new Scanner(System.in), new PrintWriter(System.out));	
	}
	
	static void genOutputs() throws IOException {
		File dir = new File("CrossBracing/in/");
		for(File f : dir.listFiles()) {
			File out = new File("CrossBracing/out/" + "out" + f.getName().substring(2));
			solve(new Scanner(new FileReader(f)), new PrintWriter(new FileWriter(out)));
		}
	}
	
	static void solve(Scanner reader, PrintWriter writer) {
		r = reader.nextInt();
		c = reader.nextInt();
		// rows: 0 to r - 1
		// columns: r to r + c - 1
		g = new ArrayList<>();
		for(int i = 0; i < r + c; i++) {
			g.add(new LinkedList<Integer>());
		}
		for(int i = 0; i < r; i++) {
			String s = reader.next();
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == '1') {
					g.get(i).add(r + j);
					g.get(r + j).add(i);
				}
			}
		}
		int min = computeMin();
		writer.println(min);
		writer.close();
		reader.close();
	}
	
	static int r, c;
	static ArrayList<LinkedList<Integer>> g;
	
	static int computeMin() {
		Integer[] component = new Integer[g.size()];
		for(int x = 0; x < g.size(); x++) {
			if(component[x] == null) {
				bfsVisit(x, component);
			}
		}
		HashSet<Integer> H = new HashSet<>();
		for(int c : component) {
			H.add(c);
		}
		return H.size() - 1;
	}
	
	static void bfsVisit(int s, Integer[] component) {
		Queue<Integer> Q = new LinkedList<>();
		Q.add(s);
		component[s] = s;
		while(!Q.isEmpty()) {
			int x = Q.poll();
			for(int y : g.get(x)) {
				if(component[y] == null) {
					Q.add(y);
					component[y] = s;
				}
			}
		}
	}
	
	// GENERATE INPUT
	
	static void genRandomInput(int C, int R, int p) throws IOException {
		Random rnd = new Random();
		PrintWriter writer = new PrintWriter(new FileWriter("CrossBracing/in/rnd_"  + C + "_" + R + "_" + p));
		writer.append(C + " " + R + "\n");
		for(int i = 0; i < C; i++) {
			for(int j = 0; j < R; j++) {
				boolean cross = true;
				for(int k = 0; k < p; k++) {
					if(rnd.nextBoolean()) cross = false;
				}
				writer.append(cross ? '1' : '0');
			}
			writer.println();
		}
		writer.close();
	}
	
}
