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
            System.out.println("0 to exit");
            System.out.print("Enter the number: ");

            int command = sc.nextInt();
            sc.nextLine(); // consume newline

            if (command == 0) {
                System.out.println("Code exited");
                break;
            }

            System.out.print("Enter Address Book name: ");
            String bookName = sc.nextLine();
            AddressBook book = dictionary.getAddressBook(bookName);
            if (book == null) {
                System.out.println("Address Book '" + bookName + "' not found. Creating new one...");
                dictionary.addAddressBook(bookName);
                book = dictionary.getAddressBook(bookName);
            }

            switch (command) {
                case 1:
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