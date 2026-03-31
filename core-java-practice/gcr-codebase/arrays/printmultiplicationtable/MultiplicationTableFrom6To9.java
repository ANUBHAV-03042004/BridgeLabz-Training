package printmultiplicationtable;
import java.util.Scanner;
public class MultiplicationTableFrom6To9 {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	       System.out.println("Enter a number :");
	       int number=sc.nextInt();
	       for(int i=0;i<10;i++)
	       {
	    	   System.out.printf("%d * %d = %d \n",number,i,number*i);
	       }
	    }
}
