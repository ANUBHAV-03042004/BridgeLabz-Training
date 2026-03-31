package addressbookwith4layerarchitecture.presentation;

import addressbookwith4layerarchitecture.dao.DictionaryDAO;
import addressbookwith4layerarchitecture.model.Contact;
import addressbookwith4layerarchitecture.service.AddressBookService;
import addressbookwith4layerarchitecture.service.ContactService;
import addressbookwith4layerarchitecture.service.DictionaryService;

import java.util.List;
import java.util.Scanner;


public class Main {


    private static final Scanner sc = new Scanner(System.in);


    private static final DictionaryDAO dictionaryDAO = new DictionaryDAO();


    private static final AddressBookService addressBookService = new AddressBookService(dictionaryDAO);
    private static final ContactService     contactService     = new ContactService(dictionaryDAO);
    private static final DictionaryService  dictionaryService  = new DictionaryService(dictionaryDAO);


    public static void main(String[] args) {


        while (true) {
            printMenu();
            int command = readInt("Enter your choice: ");

            switch (command) {
                case 0  -> { System.out.println("\nGoodbye!"); return; }
                case 1  -> handleCreateBook();
                case 2  -> handleAddContact();
                case 3  -> handleEditContact();
                case 4  -> handleDeleteContact();
                case 5  -> handleShowContacts();
                case 6  -> handleFindPerson();
                case 7  -> handleViewByCityOrState();
                case 8  -> handleCountByCityOrState();
                case 9  -> handleSortAlphabetically();
                case 10 -> handleSortByCityStateZip();
                case 11 -> handleListAllBooks();
                default -> System.out.println("Invalid choice. Please enter a number from 0 to 11.");
            }
        }
    }

  
    private static void handleCreateBook() {
        String name = readLine("Enter new Address Book name: ");
        System.out.println(addressBookService.createAddressBook(name));
    }

    private static void handleAddContact() {
        String bookName = askForExistingBook();
        if (bookName == null) return;

       
        String firstName   = readLine("First Name     : ");
        String lastName    = readLine("Last Name      : ");
        String address     = readLine("Address        : ");
        String city        = readLine("City           : ");
        String state       = readLine("State          : ");
        String zip         = readValidated("ZIP (6 digits) : ", "\\d{6}",    "Invalid – must be 6 digits.");
        String phoneNumber = readValidated("Phone (10 dig) : ", "\\d{10}",   "Invalid – must be 10 digits.");
//        String email       = readLine("Email          : ");
        String email = readValidated( "Email : ","^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Invalid email format");

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        System.out.println(contactService.addContact(bookName, contact));
    }

   
    private static void handleEditContact() {
        String bookName  = askForExistingBook();
        if (bookName == null) return;

        String firstName = readLine("First name of contact to edit : ");
        String lastName  = readLine("Last name of contact to edit  : ");
        System.out.println("Fields: firstName | lastName | address | city | state | zip | phoneNumber | email");
        String field     = readLine("Field to change : ");
        String newValue  = readLine("New value       : ");

        System.out.println(contactService.editContact(bookName, firstName, lastName, field, newValue));
    }

    private static void handleDeleteContact() {
        String bookName  = askForExistingBook();
        if (bookName == null) return;

        String firstName = readLine("First name of contact to delete : ");
        String lastName  = readLine("Last name of contact to delete  : ");
        System.out.println(contactService.deleteContact(bookName, firstName, lastName));
    }

  
    private static void handleShowContacts() {
        String bookName = askForExistingBook();
        if (bookName == null) return;

        List<Contact> contacts = contactService.getContacts(bookName);
        if (contacts == null || contacts.isEmpty()) {
            System.out.println("No contacts in '" + bookName + "'.");
        } else {
            System.out.println("\n  Contacts in '" + bookName + "'   ");
            contacts.forEach(System.out::println);
        }
    }

  
    private static void handleFindPerson() {
        String personName  = readLine("Enter full name (first last) : ");
        String cityOrState = readLine("Enter city or state          : ");
        System.out.println(dictionaryService.findPersonInCityOrState(personName, cityOrState));
    }

    private static void handleViewByCityOrState() {
        String cityOrState = readLine("Enter city or state to view : ");
        System.out.println(dictionaryService.viewByCityOrState(cityOrState));
    }


    private static void handleCountByCityOrState() {
        String cityOrState = readLine("Enter city or state to count : ");
        System.out.println(dictionaryService.countByCityOrState(cityOrState));
    }

    
    private static void handleSortAlphabetically() {
        System.out.println(dictionaryService.sortAlphabetically());
    }

  
    private static void handleSortByCityStateZip() {
        System.out.println(dictionaryService.sortByCityStateZip());
    }

 
    private static void handleListAllBooks() {
        var names = addressBookService.listAllBooks();
        if (names.isEmpty()) {
            System.out.println("No address books created yet.");
        } else {
            System.out.println("\n Address Books ");
            names.forEach(n -> System.out.println("  • " + n));
        }
    }

    
    private static String askForExistingBook() {
        String bookName = readLine("Enter Address Book name: ");
        if (!addressBookService.exists(bookName)) {
            System.out.println("Address Book '" + bookName + "' not found.");
            String create = readLine("Create it now? (yes/no): ");
            if (create.equalsIgnoreCase("yes") || create.equalsIgnoreCase("y")) {
                System.out.println(addressBookService.createAddressBook(bookName));
            } else {
                return null;
            }
        }
        return bookName;
    }

  
    private static String readLine(String prompt) {
        System.out.print(prompt);
        String line;
        do { line = sc.nextLine().trim(); }
        while (line.isEmpty());
        return line;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readValidated(String prompt, String pattern, String errMsg) {
        String value;
        do {
            value = readLine(prompt);
            if (!value.matches(pattern)) System.out.println(errMsg);
        } while (!value.matches(pattern));
        return value;
    }

 

    private static void printMenu() {
       
        System.out.println("  1  → Create new Address Book");
        System.out.println("  2  → Add contact");
        System.out.println("  3  → Edit contact");
        System.out.println("  4  → Delete contact");
        System.out.println("  5  → Show contacts in a book");
        System.out.println("  6  → Find person by name + city/state");
        System.out.println("  7  → View contacts by city or state");
        System.out.println("  8  → Count contacts by city or state");
        System.out.println("  9  → Sort alphabetically (all books)");
        System.out.println("  10 → Sort by city → state → ZIP");
        System.out.println("  11 → List all Address Books");
        System.out.println("  0  → Exit");
      
    }
}
