package addressbook;
import java.util.HashMap;

public class Dictionary {
    HashMap<String, AddressBook> dictionary = new HashMap<>();

    public AddressBook getAddressBook(String name) {
        return dictionary.get(name);
    }

    public void addAddressBook(String name) {
        if (dictionary.containsKey(name)) {
            System.out.println("Address Book with this name already exists!");
        } else {
            dictionary.put(name, new AddressBook());
            System.out.println("Address Book '" + name + "' created successfully.");
        }
    }
}