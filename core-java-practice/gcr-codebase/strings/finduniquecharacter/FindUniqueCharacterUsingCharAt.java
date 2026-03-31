package finduniquecharacter;
import java.util.Scanner;
public class FindUniqueCharacterUsingCharAt {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
System.out.println("Enter a String :");
String inputstring=sc.next();
int stringlength=findLength(inputstring);
String uniquechars=findUniqueCharacters(inputstring,stringlength);
System.out.println("unique characters :");
for(char i:uniquechars.toCharArray())
{
	System.out.println(i);
}
	}
	public static String findUniqueCharacters(String inputstring,int stringlength) {
	 String uniquechars="";

	 for(int i=0;i<stringlength;i++)
	 {
		 boolean issame=false;
		 for(int j=0;j<stringlength;j++)
		 {
			if(inputstring.charAt(i)== inputstring.charAt(j) && i!=j) {
				issame=true;
			}
		 }
		 if(!issame) {
			 uniquechars+=inputstring.charAt(i);
		 }
	 }
	 return uniquechars;
	}
	public static int findLength(String inputstring) {
		int count=0;
	for(char i:inputstring.toCharArray())
	{
		count++;
	}
		return count;
	}

}
