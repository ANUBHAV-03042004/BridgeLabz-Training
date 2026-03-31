package validatealicenseplatenumber;

public class ValidateALicensePlateNumber {
  static boolean validLicensePlate(String regex,String number) {
	  return number.matches(regex);
  }
  public static void main(String args[])
  {
	  String regex="^[A-Z]{2}\\d{4}$",number="UP3212";
	 System.out.println("Is License Plate Valid : "+validLicensePlate(regex,number));
  }
}
