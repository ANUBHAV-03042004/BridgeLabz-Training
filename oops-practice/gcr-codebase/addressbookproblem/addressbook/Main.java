package addressbook;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

        System.out.println("Welcome To Address Book Program");

        while (true) {
            System.out.println("1 to create new Address Book");
            System.out.println("2 to add contact");
            System.out.println("3 to edit contact details");
            System.out.println("4 to delete a contact");
            System.out.println("5 to show contacts");
            System.out.println("6 to search a person in contacts");
            System.out.println("7 to view person by city or state");
            System.out.println("0 to exit");
            System.out.print("Enter the number: ");

            int command = sc.nextInt();
            sc.nextLine(); // consume newline
            boolean alreadyExists = false;
            if (command == 0) {
                System.out.println("Code exited");
                break;
            }
           
            if(command == 6) {
            	System.out.println("Enter Full Name : ");
            	String Person = sc.nextLine();
            System.out.println("Enter city or state to find : ");
            String cityOrState = sc.next();
            	if(dictionary!=null) dictionary.find(Person,cityOrState);
            	else System.out.println("Dictionary is Empty");
            	continue;
            }
            if(command == 7) {
            System.out.println("Enter city or state to show : ");
            String cityOrState = sc.next();
            	if(dictionary!=null) dictionary.viewByCityOrState(cityOrState);
            	else System.out.println("Dictionary is Empty");
            	continue;
            }
            System.out.print("Enter Address Book name: ");
            String bookName = sc.nextLine();
            AddressBook book = dictionary.getAddressBook(bookName);
            
            if (book == null) {
                System.out.println("Address Book '" + bookName + "' not found. Creating new AddressBook");
                dictionary.addAddressBook(bookName);
                book = dictionary.getAddressBook(bookName);
                alreadyExists = true;
            }

            switch (command) {
                case 1:
                	if(alreadyExists) break;
                    dictionary.addAddressBook(bookName);
                    break;
                case 2:
                    if (book != null) book.addContact(sc);
                    else System.out.println("No Address Book found with that name.");
                    break;
                case 3:
                    if (book != null) System.out.println(book.editContact(sc));
                    else System.out.println("No Address Book found with that name.");
                    break;
                case 4:
                    if (book != null) book.deleteContact(sc);
                    else System.out.println("No Address Book found with that name.");
                    break;
                case 5:
                    if (book != null) book.showContacts();
                    else System.out.println("No Address Book found with that name.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}