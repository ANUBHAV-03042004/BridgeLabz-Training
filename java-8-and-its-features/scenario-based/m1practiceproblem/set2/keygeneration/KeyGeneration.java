package m1practiceproblem.set2.keygeneration;

import java.util.Scanner;

public class KeyGeneration {
	public static boolean validateInput(String input) {
		 if (input == null || input.isEmpty()) {
		        System.out.println("[Invalid Input] empty String");
		        return false;
		    }

		    if (input.contains(" ")) {
		        System.out.println("[Invalid Input] contains space");
		        return false;
		    }

		    if (input.length() < 6) {
		        System.out.println("[Invalid Input] length < 6");
		        return false;
		    }
		    
	if (input.matches(".*\\d.*")) { System.out.println("[Invalid Input] contains digit"); return false;}

	if(input.matches(".*[^a-zA-Z0-9\\s].*")) { System.out.println("Invalid Input] has special character"); return false;}
   return true;
	}
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int numberOfStrings = sc.nextInt();
	sc.nextLine();
	while(numberOfStrings-->0) {
		String input = sc.nextLine();
		 if(!validateInput(input)) continue ;
		
		 input = input.toLowerCase();
		String shortinput = input.chars().filter(c -> c%2!=0).collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).reverse().toString();
		String toggle="";
		for(int i=0;i<shortinput.length();i++) {
			if(i%2==0) toggle+= Character.toString(shortinput.charAt(i)).toUpperCase();
			else toggle+=shortinput.charAt(i);
		}
		if(toggle == null || toggle.isEmpty()) toggle = "[Invalid Input] empty String";
	System.out.println("The generated key is - "+toggle);
	}
}
}
