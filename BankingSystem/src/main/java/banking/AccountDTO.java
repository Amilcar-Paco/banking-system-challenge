package banking;

public class AccountDTO {
    private int id;
    private Long accountNumber;
    private Person person;

    public AccountDTO(int id, Long accountNumber, Person person) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Person getPerson() {
        return person;
    }
}

