package addressbookwith4layerarchitecture.dao;

import addressbookwith4layerarchitecture.model.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class DictionaryDAO {

    // Central data store: bookName → list of contacts
    private final HashMap<String, List<Contact>> store = new HashMap<>();

   
    public boolean createAddressBook(String name) {
        if (store.containsKey(name)) return false;
        store.put(name, new ArrayList<>());
        return true;
    }

   
    public List<Contact> getAddressBook(String name) {
        return store.get(name);
    }

    public boolean exists(String name) {
        return store.containsKey(name);
    }

   
    public HashMap<String, List<Contact>> getAll() {
        return store;
    }

    public Set<String> getAllBookNames() {
        return store.keySet();
    }
}
