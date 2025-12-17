package reverseastring;
// program to reverse a string
public class reverseastring {
//		main function to execute program
	public static void main (String args[])
	{
//		inputted string
		String str="vahbuna",revstr="";
//reversed string
		for(int i=str.length()-1;i>=0;i--)
		{
			revstr+=str.charAt(i);
		}
		System.out.println("User input string :"+str);
		System.out.println("reversed string :"+revstr);
		
	}
}