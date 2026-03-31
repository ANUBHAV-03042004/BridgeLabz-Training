package checkwhichnumberislargestamongthethree;

public class CheckWhichNumberIsLargestAmongTheThree {
public static void main(String args[])
{
	int num1=3,num2=9,num3=5;
	if(num1>num2 && num1>num3) System.out.println("Is the first number the largest? Yes\r\n"
			+ "Is the second number the largest? No\r\n"
			+ "Is the third number the largest? No\r\n");
	else if( num2>num1 && num2>num3) System.out.println("Is the first number the largest? No\r\n"
					+ "Is the second number the largest? Yes\r\n"
					+ "Is the third number the largest? No\r\n");
	else  System.out.println("Is the first number the largest? No\r\n"
			+ "Is the second number the largest? No\r\n"
			+ "Is the third number the largest? Yes\r\n");
}
}
