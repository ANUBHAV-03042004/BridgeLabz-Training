package orderrevenuesummary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order{
	int orderId;
	int customerId;
	double payment;
	Order(int orderId,int customerId,double payment){
		this.customerId=customerId;
		this.orderId=orderId;
		this.payment=payment;
	}
	 public int getCustomerId() { return customerId; }
	    public double getPayment() { return payment; }
 
}
public class OrderRevenue {
public static void main(String[] args) {
	Order o1= new Order(001,1,52.5d);
	Order o2= new Order(002,2,502.5d);
	Order o3= new Order(003,3,452.5d);
	Order o4= new Order(004,1,257.2d);
	Order o5= new Order(005,2,352.0d);
	Order o6= new Order(006,3,5002.1d);
	
	List<Order> order= List.of(o1,o2, o3,o4, o5, o6);
	  Map<Integer, Double> Ordertotal = order.stream().collect(Collectors.groupingBy(Order::getCustomerId,Collectors.summingDouble(Order::getPayment)));
	  Ordertotal.forEach((getCustomerId,getPayment)->{
		  System.out.println(getCustomerId+" -> "+getPayment);
	  });
	
}
}
