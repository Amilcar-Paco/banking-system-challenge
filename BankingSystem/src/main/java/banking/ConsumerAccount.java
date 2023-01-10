package banking;

import java.util.Objects;

/**
 * Account implementation for consumer (domestic) customers.
 * The account's holder is a {@link Person}.
 */
public class ConsumerAccount extends Account{
    private Person person;
    private long accountNumber;
    private int pin;
    private double currentBalance;

    public ConsumerAccount(Person person, Long accountNumber, int pin, double currentBalance, AccountType accountType) {
        super(person, accountNumber, pin, currentBalance, accountType);
        this.person = person;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.currentBalance = currentBalance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumerAccount)) return false;
        ConsumerAccount that = (ConsumerAccount) o;
        return accountNumber == that.accountNumber && pin == that.pin && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, accountNumber, pin);
    }
}
