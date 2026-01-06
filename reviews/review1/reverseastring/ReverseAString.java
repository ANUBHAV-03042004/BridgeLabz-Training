package reviews.review1.reverseastring;


public class ReverseAString {
	public static void main (String args[])
	{
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