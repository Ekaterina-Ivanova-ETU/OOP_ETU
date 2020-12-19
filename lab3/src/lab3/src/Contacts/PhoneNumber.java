package Contacts;

public class PhoneNumber {
    private PhoneType type;
    private String number;

    public PhoneNumber(PhoneType type, String number) {
        if (number == "" || number == null)
            throw new IllegalArgumentException("Incorrect phone number.");

        this.type = type;
        this.number = number;
    }

    protected void setType(PhoneType type) {
        this.type = type;
    }

    protected void setNumber(String number) {
        if (number == "" || number == null)
            throw new IllegalArgumentException("Invalid phone number.");

        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "  " + type + " " + number + "\n";
    }
}
