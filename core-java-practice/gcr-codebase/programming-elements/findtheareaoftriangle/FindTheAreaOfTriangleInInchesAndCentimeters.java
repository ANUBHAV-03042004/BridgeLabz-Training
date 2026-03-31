package findtheareaoftriangle;
import java.util.Scanner;
public class FindTheAreaOfTriangleInInchesAndCentimeters {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the base in cm : ");
	int base= sc.nextInt();
	System.out.println("Enter the height in cm : ");
	int height=sc.nextInt();
	
	float areaoftriangleincm=(float) ((1.0/2) * base * height);
	
	float areaininches= (float) (areaoftriangleincm / 2.54);
	float areainfeet =  areaininches / 12;
	System.out.printf("Area of Triangle with base: %d and height: %d is in cm %f , in inches is %f and in feet is %f",base,height,areaoftriangleincm,areaininches,areainfeet);
}
}
