package Contacts;
import java.util.ArrayList;

public class Contact {
    private  String firstname;
    private  String lastname;
    private  ArrayList<PhoneNumber> numbers;

    public Contact(String firstname, String lastname, ArrayList<PhoneNumber> numbers) {
        if (firstname == null || lastname == null || numbers == null)
            throw new IllegalArgumentException("Incorrect contact.");
        if (firstname == "" && lastname == "" && numbers.size() == 0)
            throw new IllegalArgumentException("Empty contact.");

        this.firstname = firstname;
        this.lastname = lastname;
        this.numbers = numbers;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return numbers;
    }

    protected void setFirstname(String firstname) {
        if (firstname == null)
            throw new IllegalArgumentException("Invalid firstname.");

        this.firstname = firstname;
    }

    protected void setLastname(String lastname) {
        if (lastname == null)
            throw new IllegalArgumentException("Invalid lastname.");

        this.lastname = lastname;
    }

    protected void setAllPhoneNumber(ArrayList<PhoneNumber> numbers) {
        if (numbers == null)
            throw new IllegalArgumentException("The list of phone numbers cannot be null.");

        this.numbers = numbers;
    }

    protected void setPhoneNumber(int i, String number) {
        if (i >= numbers.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        this.numbers.get(i).setNumber(number);
    }

    protected void setTypeNumber(int i, PhoneType type) {
        if (i >= numbers.size() || i < 0)
            throw new IllegalArgumentException("The index is out of bounds.");

        this.numbers.get(i).setType(type);
    }

    protected boolean checkContact (String findString) {
        if (findString == "" || findString == null)
            throw new IllegalArgumentException("The substring for searching for contacts is set incorrectly.");

        return (this.getFirstname().contains(findString) ||
                this.getLastname().contains(findString) ||
                this.getPhoneNumbers().stream().anyMatch(p-> p.getNumber().contains(findString)));
    }

    @Override
    public String toString() {
        StringBuilder contacts = new StringBuilder();
        numbers.stream().forEach(contacts::append);
        return "Contact:" + firstname + " " + lastname + "\n" + contacts;
    }
}
