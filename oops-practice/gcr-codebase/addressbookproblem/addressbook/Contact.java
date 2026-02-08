package addressbook;

import java.util.Objects;

class Contact {
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String phoneNumber;
    String email;

    public Contact(String firstName, String lastName, String address, String city,
                   String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    @Override
    public boolean equals(Object obj) {
	if(this == obj) return true;
	if (obj == null || getClass() != obj.getClass()) return false;
	Contact contact = (Contact) obj;
	return firstName.equalsIgnoreCase(contact.firstName) &&
	           lastName.equalsIgnoreCase(contact.lastName);
    }
    @Override
    public int hashCode() {
		return Objects.hash(firstName.toLowerCase(),lastName.toLowerCase());
    }
    
public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
@Override
    public String toString() {
        return firstName + " " + lastName + " address : " + address + " , " + city + " , " + state +
               " ZIP - " + zip + " phnNo. = " + phoneNumber + " @-> " + email;
    }
}