
import java.util.LinkedList;
import java.util.Scanner;

public class yunoacsol {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String x = reader.nextLine();
		LinkedList<Character> list = new LinkedList<>();
		boolean end = true;
		for(int i = 0; i < x.length(); i++) {
			if(x.charAt(i) == '<') end = false;
			else if(x.charAt(i) == '>') end = true;
			else {
				if(end) list.addLast(x.charAt(i));
				else list.addFirst(x.charAt(i));
			}
		}
		StringBuilder output = new StringBuilder();
		for(Character c : list) {
			output.append(c);
		}
		System.out.println(output);
		reader.close();
	}

}
