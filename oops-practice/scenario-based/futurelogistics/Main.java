package futurelogistics;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	Utility utility = new Utility();
	System.out.println("Enter the goods details : ");
	String input= sc.next();
	String transportDetails[]=input.split(":");
	String TransportId = transportDetails[0];
	if (!utility.validateTransportId(TransportId)) {
		sc.close();
	    return;
	}
	GoodsTransport goods = utility.parseDetails(input);
	utility.findObjectType(goods);

	sc.close();
}
}
