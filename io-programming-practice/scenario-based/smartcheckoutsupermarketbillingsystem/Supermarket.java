package smartcheckoutsupermarketbillingsystem;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Supermarket {
public static void main(String[] args) {
	Customer c1 = new Customer(List.of("Maggie","coffee","rice bag","wine"));
	Customer c2 = new Customer(List.of("Maggie","coffee"));
	Customer c3 = new Customer(List.of("Gems","chocolate","water bottle"));
	HashMap<String,List<Integer>> supermarket = new HashMap<>();
	   supermarket.put("Maggie", Arrays.asList(50, 12));        
       supermarket.put("coffee", Arrays.asList(30, 100));       
       supermarket.put("rice bag", Arrays.asList(20, 500));    
       supermarket.put("wine", Arrays.asList(10, 750));       
       supermarket.put("Gems", Arrays.asList(40, 20));          
       supermarket.put("chocolate", Arrays.asList(25, 50));     
       supermarket.put("water bottle", Arrays.asList(100, 15)); 

	Queue<Customer> checkoutCounter = new LinkedList<>();
	checkoutCounter.add(c1);
	checkoutCounter.add(c2);
	checkoutCounter.add(c3);
	while(!checkoutCounter.isEmpty()) {
		Customer customer = checkoutCounter.poll();
		int totalPrice=0;
		for(String item :customer.items) {
			List<Integer>itemdetails = supermarket.get(item);
			int stock = itemdetails.get(0);
			int price = itemdetails.get(1);
			itemdetails.set(0, --stock);
			totalPrice+=price;
		}
		System.out.println("Customer total Price : "+totalPrice);
	}
}
}
