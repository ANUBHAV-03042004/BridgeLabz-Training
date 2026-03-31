package addressbook;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AddressBook {

	  private static final Logger logger =
	            LoggerFactory.getLogger(AddressBook.class);
    List<Contact> contacts = new ArrayList<>();
    @Override
    public String toString() {
        return contacts.toString();
    }
    	public void addContact(Scanner sc) {

    	    logger.info("Enter First Name:");
    	    String firstName = sc.next();

    	    logger.info("Enter Last Name:");
    	    String lastName = sc.next();
    	    sc.nextLine();

    	    logger.info("Enter Address:");
    	    String address = sc.nextLine();

    	    logger.info("Enter City:");
    	    String city = sc.next();

    	    logger.info("Enter State:");
    	    String state = sc.next();

    	    String zip;
    	    do {
    	        logger.info("Enter Zip code (6 digits):");
    	        zip = sc.next();
    	    } while (!zip.matches("\\d{6}"));

    	    String phoneNumber;
    	    do {
    	        logger.info("Enter Phone Number (10 digits):");
    	        phoneNumber = sc.next();
    	    } while (!phoneNumber.matches("\\d{10}"));

    	    logger.info("Enter Email:");
    	    String email = sc.next();

    	    Contact newContact = new Contact(
    	            firstName, lastName, address, city,
    	            state, zip, phoneNumber, email
    	    );

    	    if (contacts.contains(newContact)) {
    	        logger.warn("Duplicate Entry Found!");
    	    } else {
    	        contacts.add(newContact);
    	        logger.info("Contact added successfully!");
    	    }
    	
    }

    public String editContact(Scanner sc) {
        logger.info("Enter the firstname of the person to edit:");
        String firstname = sc.next();
        logger.info("Enter the lastname of the person to edit:");
        String lastname = sc.next();
        String fieldChangedFrom = "";
        String field = "";
        String fieldChanged = "";

        for (Contact contact : contacts) {
            if (contact.firstName.equalsIgnoreCase(firstname) && contact.lastName.equalsIgnoreCase(lastname)) {
                logger.info("Enter the field you want to change:");
                field = sc.next();
                logger.info("Enter new value:");
                fieldChanged = sc.next();
                switch (field.toLowerCase()) {
                    case "firstname": fieldChangedFrom = contact.firstName; contact.firstName = fieldChanged; break;
                    case "lastname": fieldChangedFrom = contact.lastName; contact.lastName = fieldChanged; break;
                    case "address": fieldChangedFrom = contact.address; contact.address = fieldChanged; break;
                    case "city": fieldChangedFrom = contact.city; contact.city = fieldChanged; break;
                    case "state": fieldChangedFrom = contact.state; contact.state = fieldChanged; break;
                    case "zip": fieldChangedFrom = contact.zip; contact.zip = fieldChanged; break;
                    case "phonenumber": fieldChangedFrom = contact.phoneNumber; contact.phoneNumber = fieldChanged; break;
                    case "email": fieldChangedFrom = contact.email; contact.email = fieldChanged; break;
                    default: return "Invalid field!";
                }
                return field + " changed from " + fieldChangedFrom + " to " + fieldChanged;
            }
        }
        return "Contact not found.";
    }

    public void deleteContact(Scanner sc) {
       logger.info("Enter the firstname of the person to delete:");
        String firstname = sc.next();
        logger.info("Enter the lastname of the person to delete:");
        String lastname = sc.next();

        boolean removed = contacts.removeIf(c -> c.firstName.equalsIgnoreCase(firstname) && c.lastName.equalsIgnoreCase(lastname));
        if (removed) logger.info("Contact deleted successfully.");
        else logger.info("Contact not found.");
    }

    public void showContacts() {
        if (contacts.isEmpty()) {
            logger.info("No contacts in this Address Book.");
        } else {
            contacts.forEach(contact -> logger.info(contact.toString()));
        }
    }
}