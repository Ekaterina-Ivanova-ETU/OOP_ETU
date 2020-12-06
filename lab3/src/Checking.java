import Contacts.*;

import java.util.*;

public class Checking {
    public static void main(String[] args) {
        PhoneBook phonebook = new PhoneBook();

        ArrayList<PhoneNumber> numbers1 = new ArrayList<PhoneNumber>();
        numbers1.add(new PhoneNumber(PhoneType.MOBILE, "+79117169901"));
        numbers1.add(new PhoneNumber(PhoneType.HOME, "7352174"));
        numbers1.add(new PhoneNumber(PhoneType.WORK, "+79210050403"));
        Contact contact1 = new Contact("Will", "Smith", numbers1);
        phonebook.addContact(contact1);

        Contact contact2 = new Contact("Kate", "Iva", new ArrayList<PhoneNumber>());
        phonebook.addContact(contact2);

        ArrayList<PhoneNumber> numbers3 = new ArrayList<PhoneNumber>();
        numbers3.add(new PhoneNumber(PhoneType.MOBILE, "+79014378392"));
        numbers3.add(new PhoneNumber(PhoneType.WORK, "6444433"));
        Contact contact3 = new Contact("Zick", "Clarke", numbers3);
        phonebook.addContact(contact3);

        System.out.println("1. " + phonebook);


        System.out.println("Contacts containing \"a\":");
        System.out.println(phonebook.find("a"));
        System.out.println("Contacts containing \"44\":");
        System.out.println(phonebook.find("44"));
        System.out.println("Contacts containing \"Smih\":");
        System.out.println(phonebook.find("Smih"));

        System.out.println("Contact with the index \"0\":");
        System.out.println(phonebook.getContact(0));


        ArrayList<PhoneNumber> numbers4 = new ArrayList<PhoneNumber>();
        numbers4.add(new PhoneNumber(PhoneType.WORK, "89118080806"));
        Contact contact4 = new Contact("", "", numbers4);

        ArrayList<PhoneNumber> numbers5 = new ArrayList<PhoneNumber>();
        numbers5.add(new PhoneNumber(PhoneType.MOBILE, "+79065348901"));
        Contact contact5 = new Contact("Robb", "Li", numbers5);

        phonebook.setContact(1, contact4);
        phonebook.setContact(contact3, contact5);

        System.out.println("2. " + phonebook);


        phonebook.addContact(contact2);
        phonebook.updateFirstname(1, "Barbershop");
        phonebook.updateLastname(0, "Depp");
        phonebook.updateNumber(0, 1,"+79210055432");
        phonebook.updateTypeNumber(2, 0, PhoneType.WORK);

        System.out.println("3. " + phonebook);


        ArrayList<PhoneNumber> numbersNew = new ArrayList<PhoneNumber>();
        numbersNew.add(new PhoneNumber(PhoneType.WORK, "5064444"));
        numbersNew.add(new PhoneNumber(PhoneType.WORK, "5154444"));

        phonebook.removeContact(1);
        phonebook.removeContact(contact5);
        phonebook.removePhoneNumber(0, 1);
        phonebook.updateAllNumber(1, numbersNew);

        System.out.println("4. " + phonebook);
    }
}
