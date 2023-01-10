package banking;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Account implementation for commercial (business) customers.
 * The account's holder is a {@link Company}.
 */
public class CommercialAccount extends Account{
    private LinkedHashMap<Integer, AccountDTO> authorizedUsers = new LinkedHashMap<>();
    private Company company;
    private Long accountNumber;
    private int pin;
    private double startingDeposit;
    Random rd = new Random();

    public CommercialAccount(Company company, Long accountNumber, int pin, double startingDeposit, AccountType accountType) {
        super(company, accountNumber, pin, startingDeposit, accountType);
        this.company = company;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.startingDeposit = startingDeposit;

    }

    /**
     * Add person to list of authorized users.
     *
     * @param person The authorized user to be added to the account.
     */
    protected void addAuthorizedUser(Long accountNumber, Person person) {
        authorizedUsers.put(rd.nextInt(), new AccountDTO(rd.nextInt(), accountNumber, person));
        //System.out.println("count me");
    }

    /**
     * Verify if the person is part of the list of authorized users for this account.
     *
     * @param person
     * @return <code>true</code> if person matches an authorized user in {@link #authorizedUsers}; <code>false</code> otherwise.
     */
    public boolean isAuthorizedUser(Long aNumber, Person person) {
        AtomicBoolean isAuthorizedUser = new AtomicBoolean(false);
       authorizedUsers.forEach((key, authorizedUser) -> {
           if (authorizedUser.getAccountNumber() == aNumber && authorizedUser.getPerson().getIdNumber() == person.getIdNumber() && authorizedUser.getPerson().equals(person)) {
               isAuthorizedUser.set(true);
           }
       });
       return isAuthorizedUser.get();
    }
}
