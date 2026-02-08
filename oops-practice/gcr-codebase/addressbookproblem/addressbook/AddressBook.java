package addressbook;
import java.util.*;

class AddressBook {
    List<Contact> contacts = new ArrayList<>();
    @Override
    public String toString() {
        return contacts.toString();
    }
    public void addContact(Scanner sc) {
        System.out.println("Enter First Name:");
        String firstName = sc.next();
        System.out.println("Enter Last Name:");
        String lastName = sc.next();
        sc.nextLine(); // consume newline
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.next();
        System.out.println("Enter State:");
        String state = sc.next();
        String zip;
        do {
            System.out.println("Enter Zip code (6 digits):");
            zip = sc.next();
        } while (!zip.matches("\\d{6}"));
        String phoneNumber;
        do {
        	 System.out.println("Enter Phone Number (10 digits):");
           phoneNumber = sc.next();
        } while (!phoneNumber.matches("\\d{10}"));
        System.out.println("Enter Email:");
        String email = sc.next();
        
         
        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        
        if(contacts.contains(newContact))System.out.println("Duplicate Entry Found !");
        else { 
        	contacts.add(newContact);
        System.out.println("Contact added successfully!");
        }
    }

    public String editContact(Scanner sc) {
        System.out.println("Enter the firstname of the person to edit:");
        String firstname = sc.next();
        System.out.println("Enter the lastname of the person to edit:");
        String lastname = sc.next();
        String fieldchangedfrom = "", field = "", fieldChanged = "";

        for (Contact contact : contacts) {
            if (contact.firstName.equalsIgnoreCase(firstname) && contact.lastName.equalsIgnoreCase(lastname)) {
                System.out.println("Enter the field you want to change:");
                field = sc.next();
                System.out.println("Enter new value:");
                fieldChanged = sc.next();
                switch (field.toLowerCase()) {
                    case "firstname": fieldchangedfrom = contact.firstName; contact.firstName = fieldChanged; break;
                    case "lastname": fieldchangedfrom = contact.lastName; contact.lastName = fieldChanged; break;
                    case "address": fieldchangedfrom = contact.address; contact.address = fieldChanged; break;
                    case "city": fieldchangedfrom = contact.city; contact.city = fieldChanged; break;
                    case "state": fieldchangedfrom = contact.state; contact.state = fieldChanged; break;
                    case "zip": fieldchangedfrom = contact.zip; contact.zip = fieldChanged; break;
                    case "phonenumber": fieldchangedfrom = contact.phoneNumber; contact.phoneNumber = fieldChanged; break;
                    case "email": fieldchangedfrom = contact.email; contact.email = fieldChanged; break;
                    default: return "Invalid field!";
                }
                return field + " changed from " + fieldchangedfrom + " to " + fieldChanged;
            }
        }
        return "Contact not found.";
    }

    public void deleteContact(Scanner sc) {
        System.out.println("Enter the firstname of the person to delete:");
        String firstname = sc.next();
        System.out.println("Enter the lastname of the person to delete:");
        String lastname = sc.next();

        boolean removed = contacts.removeIf(c -> c.firstName.equalsIgnoreCase(firstname) && c.lastName.equalsIgnoreCase(lastname));
        if (removed) System.out.println("Contact deleted successfully.");
        else System.out.println("Contact not found.");
    }

    public void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts in this Address Book.");
        } else {
            contacts.forEach(System.out::println);
        }
    }
}