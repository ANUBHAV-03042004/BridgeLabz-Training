package reviews.review1.inventorymanagementsystem;
import java.util.*;
class OutOfStockException extends Exception{

	public OutOfStockException(String string) {
		super(string);
	}
}

public class Inventory implements AlertService{

//
//	Inventory(int productId, String name, int quantity, double price) {
//		super(productId, name, quantity, price);
//	}

	@Override
	public void alert() throws OutOfStockException {
		throw new OutOfStockException("Product is out of stock");
		//System.out.println("Product is Out of Stock");
	}
	
HashMap<Integer,Product> inventory= new HashMap<Integer,Product>();
//PriorityQueue<Product> stock=new PriorityQueue<>(Product.quantity=> Product.quantity.a>Product.quantity.b);
//PriorityQueue<Product> stock = new PriorityQueue<>(
//        (productA, productB) -> Integer.compare(productB.quantity, productA.quantity)
//    );
PriorityQueue<Product> stock = new PriorityQueue<>(
	    (productA, productB) -> Integer.compare(productA.quantity, productB.quantity)
	);
public void addProduct(int productId,Product product) {
	inventory.put(productId,product);
	stock.offer(product);
}
public void displayStock() {
	for(Product p:stock)
	{
		System.out.println(p.quantity);
	}
}
public void displayDetails(int productId) {
	Product product= inventory.get(productId);
	System.out.println("Product Details :");
	System.out.println(product.productId);
	System.out.println(product.name);
	System.out.println(product.quantity);
	System.out.println(product.price);
}
public void updateProduct(int productId,int quantity) {
	Product product= inventory.get(productId);
	product.quantity=quantity;
}
public int stockMonitoring(int productId) {
	Product product=inventory.get(productId);
	if(product.quantity<2)
		try {
			alert();
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
	return product.quantity;
}
public double Purchase(int ProductId,int quantityToPurchase) {
	Product product= inventory.get(ProductId);
	if(product.quantity<quantityToPurchase) {
		try {
			alert();
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
		return 0.0d;
	}
	else {
		product.quantity-=quantityToPurchase;
		return product.price*quantityToPurchase;
	}
	
}

}
