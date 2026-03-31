package stringlengthchecker;

import java.util.Scanner;
import java.util.function.Function;

public class StringLengthChecker {
	static Function<String,Integer> length = String::length;
	public static boolean checklimit(String msg,int limit) {
		int len = length.apply(msg);
		return len>limit;
	}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String input = sc.next();
System.out.println(input+" is input String is greater than limit ? "+checklimit(input,10));

}
}
