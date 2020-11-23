import java.util.Scanner;

public class yunoacsol {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String P = reader.next();
		String L = reader.next();
		L = L.substring(1, L.length() - 1);
		String[] data = L.split(",");
		int[] index = new int[] {0, data.length - 1};
		int cur = 0;
		int size = L.length() == 0 ? 0 : data.length;
		for(int i = 0; i < P.length(); i++) {
			if(P.charAt(i) == 'R') {
				cur = 1 - cur;
			} else {
				if(cur == 0) index[cur] += 1;
				else index[cur] -= 1;
				size--;
			}
		}
		if(size >= 0) {
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			if(cur == 0) {
				for(int i = index[0]; i <= index[1]; i++) {
					sb.append(data[i]);
					if(i < index[1]) sb.append(',');
				}	
			} else {
				for(int i = index[1]; i >= index[0]; i--) {
					sb.append(data[i]);
					if(i > index[0]) sb.append(',');
				}
			}
			sb.append(']');
			System.out.println(sb.toString());
		} else {
			System.out.println("error");
		}
		reader.close();
	}

}
