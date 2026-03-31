package reviews.review1.inventorymanagementsystem;

public class Main {
public static void main(String args[]) {
	Product Maggie= new Product(001,"Maggie",9,15.6d);
	Product Coffee = new Product(002,"Nescafe",8,2.5d);
	Inventory inventory= new Inventory();
	inventory.addProduct(001,Maggie);
	inventory.addProduct(002,Coffee);
	inventory.displayStock();
System.out.println("Total Price:"+inventory.Purchase(001,5));
System.out.println("Total Price:"+inventory.Purchase(001,2));
	inventory.updateProduct(001,12);
	inventory.displayDetails(001);
	System.out.println(inventory.stockMonitoring(002));

}
}
