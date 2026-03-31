package findage;
//imported to find current year
import java.time.Year;
public class FindAge {
public static void main(String args[])
{
//	year at which sam born
	int yearofage=2000,currentyear=Year.now().getValue();
	System.out.println("Sam's age is:"+ (currentyear - yearofage));
}
}
