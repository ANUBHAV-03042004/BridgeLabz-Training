package addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AddressBookMain{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome To Address Book Program");
		List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact("Anubhav", "Sharma", "123 Main Street", "Chhata", "UP", "281401", "9876543210", "anubhav@example.com"));
		contacts.add(new Contact("Priya", "Verma", "45 Park Lane", "Agra", "UP", "282001", "9123456780", "priya@example.com"));
		System.out.println("Enter the number of contacts to input : ");
		int numOfContacts=sc.nextInt();
		
		while(numOfContacts-->0) {
			 String firstName = sc.next();
			    String lastName = sc.next();
			    String address = sc.nextLine();
			    String city=sc.next();
			    String state=sc.next();
			    String zip=sc.next();
			    String phoneNumber=sc.next();
			    String email=sc.next();
			    contacts.add(new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email));
		}
		contacts.forEach(System.out::println);
	}
}