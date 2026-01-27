package addressbook;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AddressBookMain{
	public static void addContact(List<Contact>contacts,Scanner sc) {
		
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
		return ;
	}
	public static String editContact(List<Contact>contacts,Scanner sc) {
		System.out.println("Enter the firstname of the person whose details you have to change :");
		String firstname = sc.next();
		System.out.println("Enter the secondname of the person whose details you have to change :");
		String lastname=sc.next();
        String fieldchangedfrom="",field="",fieldChanged="";
        
		for(Contact contact:contacts)
		{
			if(contact.firstName.equalsIgnoreCase(firstname) && contact.lastName.equalsIgnoreCase(lastname)) {
				System.out.println("Enter the field you want to change :");
				field=sc.next();
				if(field.equalsIgnoreCase("firstname")) {
					System.out.println("Enter the firstname :");
					fieldChanged=sc.next();
					fieldchangedfrom=contact.firstName;
					contact.firstName = fieldChanged;
				}
				else if(field.equalsIgnoreCase("lastname")) {
					System.out.println("Enter the lastname : ");
					fieldChanged=sc.next();
					fieldchangedfrom=contact.lastName;
					contact.lastName=fieldChanged;
				}
				else if(field.equalsIgnoreCase("address")) {
					System.out.println("Enter the address : ");
					fieldChanged=sc.nextLine();
					fieldchangedfrom=contact.address;
					contact.address= fieldChanged;
				}
				else if(field.equalsIgnoreCase("city")) {
					System.out.println("Enter the city : ");
					fieldChanged= sc.next();
					fieldchangedfrom=contact.city;
					contact.city= fieldChanged;
				}
				else if(field.equalsIgnoreCase("state")) {
					System.out.println("Enter the state : ");
					fieldChanged= sc.next();
					fieldchangedfrom=contact.state;
					contact.state= fieldChanged;
				}
				else if(field.equalsIgnoreCase("zip")) {
					System.out.println("Enter the zip code : ");
					fieldChanged=sc.next();
					fieldchangedfrom=contact.zip;
					contact.zip= fieldChanged;
				}
				else if(field.equalsIgnoreCase("phonenumber")) {
					System.out.println("Enter the Phone number : ");
                    fieldChanged=sc.next();
                	fieldchangedfrom=contact.phoneNumber;
					contact.phoneNumber= fieldChanged;
				}
				else if(field.equalsIgnoreCase("email")) {
					System.out.println("Enter the Email : ");
					fieldChanged=sc.next();
					fieldchangedfrom=contact.email;
					contact.email= fieldChanged;
				}
			}
		}
		
		return field +" Changed from "+fieldchangedfrom+" to "+fieldChanged;
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome To Address Book Program");
		List<Contact> contacts = new ArrayList<>();
//		  contacts.add(new Contact("Anubhav", "Sharma", "123 Main Street", "Chhata", "UP", "281401", "9876543210", "anubhav@example.com"));
//	        contacts.add(new Contact("Priya", "Verma", "45 Park Lane", "Agra", "UP", "282001", "9123456780", "priya@example.com"));
		System.out.println("1 to add customer :");
		System.out.println("2 to edit customer details :");
		System.out.println("Enter the number :");
		int command = sc.nextInt();
		switch(command) {
		case 1:
			addContact(contacts,sc);
			break;
		case 2:
			 System.out.println(editContact(contacts,sc));
			break;
		}
		
		contacts.forEach(System.out::println);
	}
}