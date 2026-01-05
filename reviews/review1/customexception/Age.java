package customexception;

public class Age{
public  void checkage(int age) throws CustomException {
{
	try {
	if(age<18)
	{
		throw new CustomException("Cannot Vote");
	}
}catch(Exception e) {
	System.out.println(e);
}
}
}
public static void main(String args[]) throws CustomException
{
	Age age=new Age();
	age.checkage(5);
}
}