package Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {
    private  ArrayList<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<Contact>();
    }

    public void addContact (Contact contact) {
        if (contact == null)
            throw new IllegalArgumentException("The contact cannot be null.");

        contacts.add(contact);
    }

    public void removeContact(int i) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.remove(i);
    }

    public void removeContact(Contact contact) {
        if (contact == null)
            throw new IllegalArgumentException("The contact cannot be null.");

        int i = contacts.indexOf(contact);
        if (i == -1) {
            throw new IllegalArgumentException("The contact is not listed in the phone book.");
        }
        else {
            contacts.remove(i);
        }
    }

    public void removePhoneNumber(int indexContact, int indexPhoneNumber) {
        if (indexContact >= contacts.size() || indexContact < 0 ||
                indexPhoneNumber >= contacts.get(indexContact).getPhoneNumbers().size() || indexPhoneNumber < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(indexContact).getPhoneNumbers().remove(indexPhoneNumber);
    }

    public void setContact(int i, Contact contact) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");
        if (contact == null)
            throw new IllegalArgumentException("The contact cannot be null.");

        contacts.set(i, contact);
    }

    public void setContact(Contact OldContact, Contact NewContact) {
        if (OldContact == null || NewContact == null)
            throw new IllegalArgumentException("The contact cannot be null.");

        int i = contacts.indexOf(OldContact);
        if (i == -1) {
            throw new IllegalArgumentException("The old contact is not listed in the phone book.");
        }
        else {
            contacts.set(i, NewContact);
        }
    }

    public Contact getContact(int i) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        return contacts.get(i);
    }

    public void updateFirstname(int i, String firstname) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(i).setFirstname(firstname);
    }

    public void updateLastname(int i, String lastname) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(i).setLastname(lastname);
    }

    public void updateNumber(int indexContact, int indexPhoneNumber, String number) {
        if (indexContact >= contacts.size() || indexContact < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(indexContact).setPhoneNumber(indexPhoneNumber, number);
    }

    public void updateTypeNumber(int indexContact, int indexPhoneNumber, PhoneType type) {
        if (indexContact >= contacts.size() || indexContact < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(indexContact).setTypeNumber(indexPhoneNumber, type);
    }

    public void updateAllNumber(int i, ArrayList<PhoneNumber> numbers) {
        if (i >= contacts.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        contacts.get(i).setAllPhoneNumber(numbers);
    }

    public List<Contact> find(String findString) {
          return contacts.stream().filter(x-> x.checkContact(findString)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder pB = new StringBuilder();
        contacts.stream().forEach(pB::append);
        return "Phone Book:" + "\n" + pB.toString();
    }
}
