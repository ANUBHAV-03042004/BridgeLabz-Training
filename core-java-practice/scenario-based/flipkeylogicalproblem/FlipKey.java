package flipkeylogicalproblem;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
public class FlipKey {
	public static String CleanseAndInvert(String input) {
		if(input == null || input.length()<6) return null;
		boolean specialChars = input.chars().anyMatch( ch-> !Character.isLetterOrDigit(ch));
		if(input.contains(" ") || specialChars ) return null;
		
		input = input.toLowerCase();
		  String result = new StringBuilder(input.chars()
                  .filter(x -> x % 2 != 0)
                  .mapToObj(c -> String.valueOf((char)c))
                  .collect(java.util.stream.Collectors.joining())).reverse().toString();
		
      String ans =  IntStream.range(0,result.length()).mapToObj(i -> i%2==0 ? String.valueOf(Character.toUpperCase(result.charAt(i))):
    	 String.valueOf(result.charAt(i))).reduce("",(a,b)->a+b);
       
     return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input : ");
		String input = sc.nextLine();
	  String key =  CleanseAndInvert(input);
	  if(key == null) System.out.println("Invalid Input");
	  else System.out.println("The generated key is - "+key);
	   
	   
	}
}
