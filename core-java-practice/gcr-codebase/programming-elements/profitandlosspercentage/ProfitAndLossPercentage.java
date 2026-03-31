package profitandlosspercentage;

public class ProfitAndLossPercentage {
public static void main(String args[])
{
	
	double costPrice=40.0d,sellingPrice=45.0d,profit=sellingPrice - costPrice;
	double profitPercentage= (profit/costPrice)*100;
	System.out.println("Profit percentage would be: "+profitPercentage);
}
}
