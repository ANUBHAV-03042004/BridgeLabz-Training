package addressbook;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dictionary {

    private static final Logger logger = LoggerFactory.getLogger(Dictionary.class);
    public int getDictionarySize() {
        return dictionaryBooks.size();
    }

    HashMap<String, AddressBook> dictionaryBooks = new HashMap<>();

    public AddressBook getAddressBook(String name) {
        return dictionaryBooks.get(name);
    }

    public void addAddressBook(String name) {
        if (dictionaryBooks.containsKey(name)) {
            logger.warn("Address Book with this name already exists!");
        } else {
            dictionaryBooks.put(name, new AddressBook());
            logger.info("Address Book '{}' created successfully.", name);
        }
    }

    public void find(String person, String cityOrState) {
        String[] personToFind = person.split(" ");
        if (personToFind.length < 2) {
            logger.warn("Invalid person format. Expected 'First Last'.");
            return;
        }
        String firstName = personToFind[0];
        String lastName = personToFind[1];

        dictionaryBooks.entrySet().stream()
            .filter(entry -> entry.getValue().contacts.stream()
                .anyMatch(contact -> 
                    (contact.city.equalsIgnoreCase(cityOrState) 
                     || contact.state.equalsIgnoreCase(cityOrState))
                    && contact.firstName.equalsIgnoreCase(firstName)
                    && contact.lastName.equalsIgnoreCase(lastName)))
            .forEach(entry -> {
                logger.info("{} ->", entry.getKey());
                entry.getValue().contacts.stream()
                    .filter(contact -> 
                        (contact.city.equalsIgnoreCase(cityOrState) 
                         || contact.state.equalsIgnoreCase(cityOrState))
                        && contact.firstName.equalsIgnoreCase(firstName)
                        && contact.lastName.equalsIgnoreCase(lastName))
                    .forEach(contact -> logger.info(contact.toString()));
            });
    }

    public void viewByCityOrState(String cityOrState) {
        HashMap<String, List<Contact>> personByCity = dictionaryBooks.values().stream()
                .flatMap(entry -> entry.contacts.stream())
                .filter(contact -> contact.city.equalsIgnoreCase(cityOrState))
                .collect(Collectors.groupingBy(contact -> contact.city, HashMap::new, Collectors.toList()));

        HashMap<String, List<Contact>> personByState = dictionaryBooks.values().stream()
                .flatMap(entry -> entry.contacts.stream())
                .filter(contact -> contact.state.equalsIgnoreCase(cityOrState))
                .collect(Collectors.groupingBy(contact -> contact.state, HashMap::new, Collectors.toList()));

        personByCity.forEach((city, contacts) -> {
            logger.info("{}:", city);
            contacts.forEach(contact -> logger.info(contact.toString()));
        });

        personByState.forEach((state, contacts) -> {
            logger.info("{}:", state);
            contacts.forEach(contact -> logger.info(contact.toString()));
        });
    }

    public void countByCityOrState(String cityOrState) {
        long countByCity = dictionaryBooks.values().stream()
                .flatMap(entry -> entry.contacts.stream())
                .filter(contact -> contact.city.equalsIgnoreCase(cityOrState))
                .count();

        if (countByCity > 0)
            logger.info("Count By City: {}", countByCity);
        else
            logger.info("Count is 0");

        long countByState = dictionaryBooks.values().stream()
                .flatMap(entry -> entry.contacts.stream())
                .filter(contact -> contact.state.equalsIgnoreCase(cityOrState))
                .count();

        if (countByState > 0)
            logger.info("Count By State: {}", countByState);
        else
            logger.info("Count is 0");
    }

    public void sortAlphabetically() {
        for (AddressBook book : dictionaryBooks.values()) {
            Collections.sort(book.contacts,
                    Comparator.comparing((Contact c) -> c.firstName, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(c -> c.lastName, String.CASE_INSENSITIVE_ORDER));
        }
        dictionaryBooks.values().forEach(book -> logger.info(book.toString()));
    }

    public void sortByCityStateOrZip() {
        for (AddressBook book : dictionaryBooks.values()) {
            Collections.sort(book.contacts,
                    Comparator.comparing((Contact c) -> c.city, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(c -> c.state, String.CASE_INSENSITIVE_ORDER)
                            .thenComparingInt(c -> {
                                try { return Integer.parseInt(c.zip); } 
                                catch(NumberFormatException e) { return 0; }
                            }));
        }
        dictionaryBooks.values().forEach(book -> logger.info(book.toString()));
    }
}
