import java.util.LinkedList;
import java.util.Scanner;

public class ReadStringsToList {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		LinkedList<String> stringList = new LinkedList<>();
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			stringList.add(line);
		}
		
		// print the number of elements
		System.out.println(stringList.size());

		// iterate over the elements
		for(String s : stringList) {
			System.out.println(s);
		}

		// get the first element
		String first = stringList.getFirst();
		System.out.println("first: " + first);

		// get the last element
		String last = stringList.getLast();
		System.out.println("last: " + last);

		// remove the last element
		stringList.removeLast();

		// remove the last element
		stringList.removeFirst();

		// get the first element
		first = stringList.getFirst();
		System.out.println("new first: " + first);

		// get the last element
		last = stringList.getLast();
		System.out.println("new last: " + last);

		reader.close();
	}

}
