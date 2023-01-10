package banking;

import java.util.Objects;

/**
 * The concrete Account holder of Person type.
 */
public class Person extends AccountHolder{
    private String firstName;
    private String lastName;
    private int idNumber;

    public Person(String firstName, String lastName, int idNumber){
        super(idNumber);
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getIdNumber() == person.getIdNumber() && getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getIdNumber());
    }
}
