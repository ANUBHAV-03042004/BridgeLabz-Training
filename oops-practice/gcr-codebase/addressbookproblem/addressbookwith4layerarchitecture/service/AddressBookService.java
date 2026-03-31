package addressbookwith4layerarchitecture.service;

import addressbookwith4layerarchitecture.dao.DictionaryDAO;

import java.util.Set;


public class AddressBookService {

    private final DictionaryDAO dictionaryDAO;

    public AddressBookService(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }


    public String createAddressBook(String name) {
        if (name == null || name.isBlank())
            return "Address Book name cannot be empty.";

        boolean created = dictionaryDAO.createAddressBook(name);
        return created
                ? "Address Book '" + name + "' created successfully."
                : "Address Book '" + name + "' already exists!";
    }

   
    public boolean exists(String name) {
        return dictionaryDAO.exists(name);
    }

    public Set<String> listAllBooks() {
        return dictionaryDAO.getAllBookNames();
    }
}
