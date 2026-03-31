package addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    private Dictionary dictionary;

    @BeforeEach
    void setup() {
        dictionary = new Dictionary();
    }

    @Test
    void testAddAddressBookSuccessfully() {
        dictionary.addAddressBook("Friends");
        assertNotNull(dictionary.getAddressBook("Friends"));
    }

    @Test
    void testAddDuplicateAddressBookNotAdded() {
        dictionary.addAddressBook("Friends");
        dictionary.addAddressBook("Friends");
        assertEquals(1, dictionary.getDictionarySize());
    }

    @Test
    void testGetAddressBookReturnsNullIfNotExists() {
        assertNull(dictionary.getAddressBook("Unknown"));
    }

    @Test
    void testFindByCityAndName() {
        dictionary.addAddressBook("Family");
        AddressBook addressBook = dictionary.getAddressBook("Family");
        addressBook.contacts.add(new Contact(
                "John", "Doe", "Addr", "Pune", "MH", "411001", "9876543210", "john@test.com"
        ));

        assertDoesNotThrow(() -> dictionary.find("John Doe", "Pune"));
    }

    @Test
    void testFindInvalidPersonFormat() {
        dictionary.addAddressBook("Family");
        AddressBook addressBook = dictionary.getAddressBook("Family");
        addressBook.contacts.add(new Contact(
                "Jane", "Doe", "Addr", "Mumbai", "MH", "400001", "9876543211", "jane@test.com"
        ));

        assertDoesNotThrow(() -> dictionary.find("Jane", "Mumbai"));
    }

    @Test
    void testViewByCityOrStateDoesNotThrowException() {
        dictionary.addAddressBook("Friends");
        AddressBook addressBook = dictionary.getAddressBook("Friends");
        addressBook.contacts.add(new Contact(
                "Jane", "Doe", "Addr", "Mumbai", "MH", "400001", "9876543211", "jane@test.com"
        ));

        assertDoesNotThrow(() -> dictionary.viewByCityOrState("Mumbai"));
        assertDoesNotThrow(() -> dictionary.viewByCityOrState("MH"));
    }

    @Test
    void testCountByCityOrStateDoesNotThrowException() {
        dictionary.addAddressBook("Office");
        AddressBook addressBook = dictionary.getAddressBook("Office");
        addressBook.contacts.add(new Contact(
                "Sam", "Smith", "Addr", "Delhi", "DL", "110001", "9876543222", "sam@test.com"
        ));

        assertDoesNotThrow(() -> dictionary.countByCityOrState("Delhi"));
        assertDoesNotThrow(() -> dictionary.countByCityOrState("DL"));
    }

    @Test
    void testSortAlphabetically() {
        dictionary.addAddressBook("SortedBook");
        AddressBook addressBook = dictionary.getAddressBook("SortedBook");
        addressBook.contacts.add(new Contact(
                "Zack", "Alpha", "Addr", "City", "ST", "123456", "9000000001", "z@test.com"
        ));
        addressBook.contacts.add(new Contact(
                "Adam", "Beta", "Addr", "City", "ST", "123457", "9000000002", "a@test.com"
        ));

        dictionary.sortAlphabetically();

        assertEquals("Adam", addressBook.contacts.get(0).firstName);
        assertEquals("Zack", addressBook.contacts.get(1).firstName);
    }

    @Test
    void testSortByCityStateOrZip() {
        dictionary.addAddressBook("LocationSort");
        AddressBook addressBook = dictionary.getAddressBook("LocationSort");
        addressBook.contacts.add(new Contact(
                "John", "Doe", "Addr", "Pune", "MH", "411002", "9876543210", "j@test.com"
        ));
        addressBook.contacts.add(new Contact(
                "Jane", "Doe", "Addr", "Pune", "MH", "411001", "9876543211", "j2@test.com"
        ));

        dictionary.sortByCityStateOrZip();

        assertEquals("411001", addressBook.contacts.get(0).zip);
        assertEquals("411002", addressBook.contacts.get(1).zip);
    }
}
