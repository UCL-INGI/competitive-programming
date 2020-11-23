import java.util.Scanner;

public class EggSqrt {

public static void main(String[] args) {
Scanner reader = new Scanner(System.in);
int f = reader.nextInt();
int sq = (int)Math.sqrt(f);
int next = sq;
int last = 1;
while(true) {
System.out.println("drop " + next);
int state = reader.nextInt();
if(state == 0) { // alive
last = next;
next += sq;
} else { // broken
if(last + 1 == next - 1) {
System.out.println("found " + (last + 1));
} else {
for(int i = last + 1; i < next; i++) {
System.out.println("drop " + i);
state = reader.nextInt();
if(state == 1) {
System.out.println("found " + i);
break;
}
}
}
break;
}
}
reader.close();
}

}

