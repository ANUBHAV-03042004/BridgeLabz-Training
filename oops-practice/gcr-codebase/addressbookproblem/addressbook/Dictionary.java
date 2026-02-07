package addressbook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

	public void find(String person,String cityOrState) {
		Stream<Map.Entry<String,AddressBook>> foundMatch = dictionary.entrySet().stream().filter(
				entry -> entry.getValue().contacts.stream()
		        .anyMatch(contact -> 
		            contact.city.equalsIgnoreCase(cityOrState) ||
		            contact.state.equalsIgnoreCase(cityOrState)));
				foundMatch.forEach(entry -> {
				    System.out.println(entry.getKey() + " -> ");
				    entry.getValue().contacts.stream()
				        .filter(contact -> 
				            contact.city.equalsIgnoreCase(cityOrState) ||
				            contact.state.equalsIgnoreCase(cityOrState)
				        )
				        .forEach(System.out::println);
				});

		
	}
}