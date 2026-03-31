package addressbook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testContactConstructorAndGetters() {
        Contact contact = new Contact(
                "John",
                "Doe",
                "123 Main St",
                "NewYork",
                "NY",
                "123456",
                "9876543210",
                "john@example.com"
        );

        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
    }

    @Test
    void testEqualsSameObject() {
        Contact contact = new Contact(
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "email@test.com"
        );

        assertEquals(contact, contact);
    }

    @Test
    void testEqualsDifferentObjectsSameNameDifferentCase() {
        Contact c1 = new Contact(
                "John", "Doe", "Addr1", "City1", "State1", "123456", "9876543210", "a@test.com"
        );

        Contact c2 = new Contact(
                "john", "doe", "Addr2", "City2", "State2", "654321", "0123456789", "b@test.com"
        );

   
        assertEquals(c2, c1);
        assertEquals(c2.hashCode(), c1.hashCode());
    }


    @Test
    void testNotEqualsDifferentFirstName() {
        Contact c1 = new Contact(
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "a@test.com"
        );

        Contact c2 = new Contact(
                "Jane", "Doe", "Addr", "City", "State", "123456", "9876543210", "a@test.com"
        );

        assertNotEquals(c1, c2);
    }

    @Test
    void testNotEqualsDifferentLastName() {
        Contact c1 = new Contact(
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "a@test.com"
        );

        Contact c2 = new Contact(
                "John", "Smith", "Addr", "City", "State", "123456", "9876543210", "a@test.com"
        );

        assertNotEquals(c1, c2);
    }

    @Test
    void testNotEqualsNullAndDifferentClass() {
        Contact contact = new Contact(
                "John", "Doe", "Addr", "City", "State", "123456", "9876543210", "a@test.com"
        );


        assertNotEquals(null,contact);
        assertNotEquals( "John Doe",contact);
    }

    @Test
    void testToStringContainsImportantFields() {
        Contact contact = new Contact(
                "John",
                "Doe",
                "123 Main St",
                "NewYork",
                "NY",
                "123456",
                "9876543210",
                "john@example.com"
        );

        String result = contact.toString();

        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("123 Main St"));
        assertTrue(result.contains("9876543210"));
        assertTrue(result.contains("john@example.com"));
    }
}
