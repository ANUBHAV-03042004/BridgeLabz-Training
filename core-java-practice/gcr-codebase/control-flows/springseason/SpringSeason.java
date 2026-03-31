package springseason;

public class SpringSeason {
public static void main(String args[])
{
	int month = Integer.parseInt(args[0]);
	int day = Integer.parseInt(args[1]);

if(month == 3 && (day>=20 && day<=31))System.out.println("It's a Spring Season");
else if(month == 4 && day>0 && day<31) System.out.println("It's a Spring Season");
else if(month == 5 && day>0 && day<=31) System.out.println("It's a Spring Season");
else if (month == 6 && (day>=20 && day<31))System.out.println("It's a Spring Season");
else System.out.println("It's not a Spring Season");
}
}