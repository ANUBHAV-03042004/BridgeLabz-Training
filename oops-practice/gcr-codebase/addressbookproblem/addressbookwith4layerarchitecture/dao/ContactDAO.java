package addressbookwith4layerarchitecture.dao;

import addressbookwith4layerarchitecture.model.Contact;

import java.util.List;
import java.util.Optional;

public class ContactDAO {

    public boolean add(List<Contact> contacts, Contact contact) {
        if (contacts.contains(contact)) return false;
        contacts.add(contact);
        return true;
    }

    
    public Optional<Contact> findByName(List<Contact> contacts, String firstName, String lastName) {
        return contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName) &&
                             c.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

   
    public boolean delete(List<Contact> contacts, String firstName, String lastName) {
        return contacts.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(firstName) &&
                c.getLastName().equalsIgnoreCase(lastName));
    }

 
    public List<Contact> getAll(List<Contact> contacts) {
        return contacts;
    }
}
