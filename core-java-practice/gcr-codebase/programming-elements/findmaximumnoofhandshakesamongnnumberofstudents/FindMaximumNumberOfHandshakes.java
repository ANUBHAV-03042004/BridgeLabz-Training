package findmaximumnoofhandshakesamongnnumberofstudents;
import java.util.Scanner;
public class FindMaximumNumberOfHandshakes {
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
System.out.println("Enter the number of people : ");
int numberofpeople=sc.nextInt();
int number_of_handshakes=(numberofpeople*(numberofpeople-1))/2;
System.out.printf("Number of possible handshakes  will be : %d ", number_of_handshakes);
}
}
