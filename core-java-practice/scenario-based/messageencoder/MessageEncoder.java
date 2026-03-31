package messageencoder;

import java.util.Scanner;

public class MessageEncoder {
	public static boolean checklength(String msg) {
		if(msg.length()<5) return false;
		return true;
	}
	public static boolean containsSpace(String msg) {
		String len[]=msg.split(" ");
		if(len.length==1) return true;
	return false;
	}
	public static String encodemsg(String message)
	{
		int len = message.length();
	String encoded_msg = "";
	  for(char i:message.toCharArray()) {
		  encoded_msg += (char) (i-len); 
	  }
		return encoded_msg;
	}
public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter the message : ");
	String message = sc.nextLine();
	if(!checklength(message)) {
		System.out.println("The String "+message+" has minimum length");
		return ; 
	}
	if(!containsSpace(message)) {
		System.out.println("The String "+message+" should not contain space");
		return;
	}
	 System.out.println("Encoded Message : " +encodemsg(message));
}


}
