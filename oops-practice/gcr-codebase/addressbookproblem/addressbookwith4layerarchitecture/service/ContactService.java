package addressbookwith4layerarchitecture.service;

import addressbookwith4layerarchitecture.dao.ContactDAO;
import addressbookwith4layerarchitecture.dao.DictionaryDAO;
import addressbookwith4layerarchitecture.model.Contact;

import java.util.List;
import java.util.Optional;

public class ContactService {

    private final ContactDAO    contactDAO;
    private final DictionaryDAO dictionaryDAO;

    public ContactService(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
        this.contactDAO    = new ContactDAO();
    }

    
    public boolean isValidZip(String zip) {
        return zip != null && zip.matches("\\d{6}");
    }

  
    public boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }


    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
    }

    
    public String addContact(String bookName, Contact contact) {
        List<Contact> contacts = dictionaryDAO.getAddressBook(bookName);
        if (contacts == null) return "Error: Address Book '" + bookName + "' not found.";

        boolean added = contactDAO.add(contacts, contact);
        return added ? "Contact added successfully!" : "Duplicate entry! A contact with this name already exists.";
    }

  
    public String editContact(String bookName, String firstName, String lastName,
                              String field, String newValue) {
        List<Contact> contacts = dictionaryDAO.getAddressBook(bookName);
        if (contacts == null) return "Error: Address Book '" + bookName + "' not found.";

     
        switch (field.toLowerCase()) {
            case "zip":
                if (!isValidZip(newValue)) return "Invalid ZIP code. Must be exactly 6 digits.";
                break;
            case "phonenumber":
                if (!isValidPhone(newValue)) return "Invalid phone number. Must be exactly 10 digits.";
                break;
            case "email":
                if (!isValidEmail(newValue)) return "Invalid email address format.";
                break;
        }

        Optional<Contact> opt = contactDAO.findByName(contacts, firstName, lastName);
        if (opt.isEmpty()) return "Contact '" + firstName + " " + lastName + "' not found.";

        Contact contact  = opt.get();
        String  oldValue;

        switch (field.toLowerCase()) {
            case "firstname":    oldValue = contact.getFirstName();   contact.setFirstName(newValue);   break;
            case "lastname":     oldValue = contact.getLastName();    contact.setLastName(newValue);    break;
            case "address":      oldValue = contact.getAddress();     contact.setAddress(newValue);     break;
            case "city":         oldValue = contact.getCity();        contact.setCity(newValue);        break;
            case "state":        oldValue = contact.getState();       contact.setState(newValue);       break;
            case "zip":          oldValue = contact.getZip();         contact.setZip(newValue);         break;
            case "phonenumber":  oldValue = contact.getPhoneNumber(); contact.setPhoneNumber(newValue); break;
            case "email":        oldValue = contact.getEmail();       contact.setEmail(newValue);       break;
            default: return "Invalid field '" + field + "'. Valid fields: firstName, lastName, address, city, state, zip, phoneNumber, email.";
        }
        return "Updated: '" + field + "' changed from '" + oldValue + "' → '" + newValue + "'";
    }

   
    public String deleteContact(String bookName, String firstName, String lastName) {
        List<Contact> contacts = dictionaryDAO.getAddressBook(bookName);
        if (contacts == null) return "Error: Address Book '" + bookName + "' not found.";

        boolean removed = contactDAO.delete(contacts, firstName, lastName);
        return removed
                ? "Contact '" + firstName + " " + lastName + "' deleted successfully."
                : "Contact '" + firstName + " " + lastName + "' not found.";
    }

  
    public List<Contact> getContacts(String bookName) {
        return dictionaryDAO.getAddressBook(bookName);
    }
}
