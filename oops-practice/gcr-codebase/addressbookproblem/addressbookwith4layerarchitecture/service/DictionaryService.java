package addressbookwith4layerarchitecture.service;

import addressbookwith4layerarchitecture.dao.DictionaryDAO;
import addressbookwith4layerarchitecture.model.Contact;

import java.util.*;
import java.util.stream.Collectors;


public class DictionaryService {

    private final DictionaryDAO dictionaryDAO;

    public DictionaryService(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }

    public String findPersonInCityOrState(String personName, String cityOrState) {
        String[] parts     = personName.trim().split("\\s+", 2);
        String   firstName = parts[0];
        String   lastName  = parts.length > 1 ? parts[1] : "";

        StringBuilder sb    = new StringBuilder();
        boolean       found = false;

        for (Map.Entry<String, List<Contact>> entry : dictionaryDAO.getAll().entrySet()) {
            List<Contact> matches = entry.getValue().stream()
                    .filter(c -> c.getFirstName().equalsIgnoreCase(firstName) &&
                                 (lastName.isEmpty() || c.getLastName().equalsIgnoreCase(lastName)) &&
                                 (c.getCity().equalsIgnoreCase(cityOrState) ||
                                  c.getState().equalsIgnoreCase(cityOrState)))
                    .collect(Collectors.toList());

            if (!matches.isEmpty()) {
                sb.append("  Address Book: ").append(entry.getKey()).append("\n");
                matches.forEach(c -> sb.append("    ").append(c).append("\n"));
                found = true;
            }
        }
        return found ? sb.toString() : "No contact named '" + personName + "' found in '" + cityOrState + "'.";
    }

    public String viewByCityOrState(String cityOrState) {
        Map<String, List<Contact>> byCity = dictionaryDAO.getAll().values().stream()
                .flatMap(List::stream)
                .filter(c -> c.getCity().equalsIgnoreCase(cityOrState))
                .collect(Collectors.groupingBy(Contact::getCity));

        Map<String, List<Contact>> byState = dictionaryDAO.getAll().values().stream()
                .flatMap(List::stream)
                .filter(c -> c.getState().equalsIgnoreCase(cityOrState))
                .collect(Collectors.groupingBy(Contact::getState));

        if (byCity.isEmpty() && byState.isEmpty())
            return "No contacts found in city/state: '" + cityOrState + "'.";

        StringBuilder sb = new StringBuilder();
        byCity.forEach((city, contacts) -> {
            sb.append("City – ").append(city).append(":\n");
            contacts.forEach(c -> sb.append("  ").append(c).append("\n"));
        });
        byState.forEach((state, contacts) -> {
            sb.append("State – ").append(state).append(":\n");
            contacts.forEach(c -> sb.append("  ").append(c).append("\n"));
        });
        return sb.toString();
    }

  
    public String countByCityOrState(String cityOrState) {
        long cityCount = dictionaryDAO.getAll().values().stream()
                .flatMap(List::stream)
                .filter(c -> c.getCity().equalsIgnoreCase(cityOrState))
                .count();

        long stateCount = dictionaryDAO.getAll().values().stream()
                .flatMap(List::stream)
                .filter(c -> c.getState().equalsIgnoreCase(cityOrState))
                .count();

        if (cityCount == 0 && stateCount == 0)
            return "No contacts found for '" + cityOrState + "'.";

        StringBuilder sb = new StringBuilder();
        if (cityCount  > 0) sb.append("Count by City  '").append(cityOrState).append("': ").append(cityCount).append("\n");
        if (stateCount > 0) sb.append("Count by State '").append(cityOrState).append("': ").append(stateCount).append("\n");
        return sb.toString();
    }

  
    public String sortAlphabetically() {
        dictionaryDAO.getAll().values().forEach(contacts ->
                contacts.sort(Comparator
                        .comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER)));

        return formatAllBooks("Sorted Alphabetically");
    }

   
    public String sortByCityStateZip() {
        dictionaryDAO.getAll().values().forEach(contacts ->
                contacts.sort(Comparator
                        .comparing(Contact::getCity, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getState, String.CASE_INSENSITIVE_ORDER)
                        .thenComparingInt(c -> {
                            try { return Integer.parseInt(c.getZip()); }
                            catch (NumberFormatException e) { return 0; }
                        })));

        return formatAllBooks("Sorted by City → State → ZIP");
    }

   
    private String formatAllBooks(String header) {
        if (dictionaryDAO.getAll().isEmpty()) return "No address books found.";
        StringBuilder sb = new StringBuilder("=== ").append(header).append(" ===\n");
        dictionaryDAO.getAll().forEach((bookName, contacts) -> {
            sb.append("\n[ ").append(bookName).append(" ]\n");
            if (contacts.isEmpty()) sb.append("  (empty)\n");
            else contacts.forEach(c -> sb.append("  ").append(c).append("\n"));
        });
        return sb.toString();
    }
}
