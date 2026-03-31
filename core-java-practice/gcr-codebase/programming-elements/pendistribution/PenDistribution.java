package pendistribution;

public class PenDistribution {
public static void main(String args[])
{
	int totalPen=14,totalStudent=3;
	int totaldistributedpens= totalPen / totalStudent;
	int leftoverPens= totalPen % totalStudent;
	System.out.printf("%d pens we have to distribute in %d students. Each got %d pens and %d pens are left.",totalPen,totalStudent,totaldistributedpens,leftoverPens);
}
}
