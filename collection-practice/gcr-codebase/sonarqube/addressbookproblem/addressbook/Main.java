package addressbook;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String DICTIONARY_EMPTY = "Dictionary is Empty";
    private static final String ADDRESSBOOK_NOT_FOUND = "No Address Book found with that name.";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

        logger.info("Welcome To Address Book Program");

        while (true) {
            printMenu();

            int command = sc.nextInt();
            sc.nextLine(); // consume newline

            if (command == 0) {
                logger.info("Code exited");
                break;
            }

            handleCommand(command, sc, dictionary);
        }

        sc.close();
    }

    private static void printMenu() {
        logger.info("""
                1 to create new Address Book
                2 to add contact
                3 to edit contact details
                4 to delete a contact
                5 to show contacts
                6 to search a person in contacts
                7 to view person by city or state
                8 to view the count of person by city or state
                9 to sort the entries in the addressbook alphabetically
                10 to sort the entries in the addressbook by city, state or zip
                0 to exit
                Enter the number:""");
    }

    private static void handleCommand(int command, Scanner sc, Dictionary dictionary) {
        switch (command) {
            case 1 -> createAddressBook(sc, dictionary);
            case 2 -> addContact(sc, dictionary);
            case 3 -> editContact(sc, dictionary);
            case 4 -> deleteContact(sc, dictionary);
            case 5 -> showContacts(sc, dictionary);
            case 6 -> searchPerson(sc, dictionary);
            case 7 -> viewByCityOrState(sc, dictionary);
            case 8 -> countByCityOrState(sc, dictionary);
            case 9 -> sortAlphabetically(dictionary);
            case 10 -> sortByCityStateOrZip(dictionary);
            default -> logger.warn("Invalid choice, please try again.");
        }
    }

    // --- Refactored helper methods ---

    private static void createAddressBook(Scanner sc, Dictionary dictionary) {
        logger.info("Enter Address Book name: ");
        String bookName = sc.nextLine().trim();
        dictionary.addAddressBook(bookName);
    }

    private static AddressBook getAddressBookFromUser(Scanner sc, Dictionary dictionary) {
        logger.info("Enter Address Book name: ");
        String bookName = sc.nextLine().trim();
        AddressBook book = dictionary.getAddressBook(bookName);

        if (book == null) {
            logger.warn(ADDRESSBOOK_NOT_FOUND);
        }

        return book;
    }

    private static void addContact(Scanner sc, Dictionary dictionary) {
        AddressBook book = getAddressBookFromUser(sc, dictionary);
        if (book != null) book.addContact(sc);
    }

    private static void editContact(Scanner sc, Dictionary dictionary) {
        AddressBook book = getAddressBookFromUser(sc, dictionary);
        if (book != null) {
            String result = book.editContact(sc);
            logger.info(result);
        }
    }

    private static void deleteContact(Scanner sc, Dictionary dictionary) {
        AddressBook book = getAddressBookFromUser(sc, dictionary);
        if (book != null) book.deleteContact(sc);
    }

    private static void showContacts(Scanner sc, Dictionary dictionary) {
        AddressBook book = getAddressBookFromUser(sc, dictionary);
        if (book != null) book.showContacts();
    }

    private static void searchPerson(Scanner sc, Dictionary dictionary) {
        logger.info("Enter Full Name: ");
        String person = sc.nextLine().trim();
        logger.info("Enter city or state to find: ");
        String cityOrState = sc.nextLine().trim();
        if (dictionary != null) dictionary.find(person, cityOrState);
        else logger.warn(DICTIONARY_EMPTY);
    }

    private static void viewByCityOrState(Scanner sc, Dictionary dictionary) {
        logger.info("Enter city or state to show: ");
        String cityOrState = sc.nextLine().trim();
        if (dictionary != null) dictionary.viewByCityOrState(cityOrState);
        else logger.warn(DICTIONARY_EMPTY);
    }

    private static void countByCityOrState(Scanner sc, Dictionary dictionary) {
        logger.info("Enter city or state to show the count: ");
        String cityOrState = sc.nextLine().trim();
        if (dictionary != null) dictionary.countByCityOrState(cityOrState);
        else logger.warn(DICTIONARY_EMPTY);
    }

    private static void sortAlphabetically(Dictionary dictionary) {
        if (dictionary != null) dictionary.sortAlphabetically();
        else logger.warn(DICTIONARY_EMPTY);
    }

    private static void sortByCityStateOrZip(Dictionary dictionary) {
        if (dictionary != null) dictionary.sortByCityStateOrZip();
        else logger.warn(DICTIONARY_EMPTY);
    }
}
