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
	
		contacts.forEach(System.out::println);
	}
}