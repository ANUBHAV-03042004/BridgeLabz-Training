package customsortinginecommerce;

import java.util.ArrayList;
import java.util.Scanner;

class Product{
	String name;
	double price;
	float rating;
	int discount;
	Product(String name,double price,float rating,int discount){
		this.discount=discount;
		this.name=name;
		this.price=price;
		this.rating=rating;
	}
	@Override
	public String toString() {
		return name+":"+ discount+"-"+ rating+" ->"+price;
	}
}
public class CustomSortingInECommerce {
public static void main(String[] args) {
	ArrayList<Product> products = new ArrayList<>();
	Product Earbuds= new Product("Earbuds",4500.5d,4.5f,10);
	Product Laptop= new Product("Laptop",5500.50d,3.5f,15);
	Product MobilePhone= new Product("Samsung",3500.5d,4.2f,20);
	products.add(MobilePhone);
	products.add(Laptop);
	products.add(Earbuds);
	 Scanner sc = new Scanner(System.in);
     System.out.println("Choose sorting criteria: price / rating / discount");
     String criteria = sc.nextLine();

    
     switch (criteria.toLowerCase()) {
     case "price":
    	 products.sort((p1,p2)->Double.compare(p2.price, p1.price));
    	 break;
     case "rating":
    	 products.sort((p1,p2)->Float.compare(p2.rating, p1.rating));
    	 break;
     case "discount":
    	 products.sort((p1,p2)->Integer.compare(p2.discount, p1.discount));
    	 break;
     case "default":
    	 System.out.println("Invalid list");
     }
     System.out.println("Sorted : ");
     products.forEach(System.out::println);

}
}
