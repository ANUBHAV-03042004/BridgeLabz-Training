package addressbook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class AddressBookTest {

    @Test
    void testAddContactSuccessfully() {
        AddressBook book = new AddressBook();

        String input = String.join("\n",
                "John",
                "Doe",
                "123 Main Street",
                "NewYork",
                "NY",
                "123456",
                "9876543210",
                "john@example.com"
        );

        Scanner sc = new Scanner(input);
        book.addContact(sc);

        assertEquals(1, book.contacts.size());
        assertEquals("John", book.contacts.get(0).firstName);
    }

    @Test
    void testDuplicateContactNotAdded() {
        AddressBook book = new AddressBook();

        String input = String.join("\n",
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "john@example.com",
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "john@example.com"
        );

        Scanner sc = new Scanner(input);
        book.addContact(sc);
        book.addContact(sc);

        assertEquals(1, book.contacts.size());
    }

    @Test
    void testEditContactFirstName() {
        AddressBook book = new AddressBook();

        String addInput = String.join("\n",
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "john@example.com"
        );
        book.addContact(new Scanner(addInput));

        String editInput = String.join("\n",
                "John",
                "Doe",
                "firstname",
                "Johnny"
        );

        String result = book.editContact(new Scanner(editInput));

        assertEquals("firstname changed from John to Johnny", result);
        assertEquals("Johnny", book.contacts.get(0).firstName);
    }

    @Test
    void testEditContactNotFound() {
        AddressBook book = new AddressBook();

        String editInput = String.join("\n",
                "Jane",
                "Doe"
        );

        String result = book.editContact(new Scanner(editInput));

        assertEquals("Contact not found.", result);
    }

    @Test
    void testDeleteContactSuccessfully() {
        AddressBook book = new AddressBook();

        String addInput = String.join("\n",
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "john@example.com"
        );
        book.addContact(new Scanner(addInput));

        String deleteInput = String.join("\n",
                "John",
                "Doe"
        );

        book.deleteContact(new Scanner(deleteInput));

        assertTrue(book.contacts.isEmpty());
    }

    @Test
    void testDeleteContactNotFound() {
        AddressBook book = new AddressBook();

        String deleteInput = String.join("\n",
                "Jane",
                "Doe"
        );

        book.deleteContact(new Scanner(deleteInput));

        assertEquals(0, book.contacts.size());
    }
}
