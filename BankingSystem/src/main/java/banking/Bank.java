package banking;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;
    Random rd;
    CommercialAccount commercialAccount;
    ConsumerAccount consumerAccount;
    Integer idNumber = null;

    public Bank() {
        accounts = new LinkedHashMap<>();
        rd = new Random();
    }

    private Account getAccount(Long accountNumber) {
        AtomicReference<Account> object = new AtomicReference<>();
        if (!accounts.isEmpty()) {
            accounts.forEach((aLong, account) -> {
                if (aLong == accountNumber){
                    object.set(account);
                }
            });
        }
        return object.get();
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        Long accountNumber = previousAccountNumber()+1;
        commercialAccount = new CommercialAccount(company, accountNumber, pin, startingDeposit, AccountType.COMMERCIAL);
        accounts.put(accountNumber, commercialAccount);
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        Long accountNumber = previousAccountNumber()+1;
        consumerAccount = new ConsumerAccount(person, accountNumber, pin, startingDeposit, AccountType.CONSUMER);
        accounts.put(accountNumber, consumerAccount);
       return accountNumber;
    }

    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.creditAccount(amount);
        }
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.debitAccount(amount);
        }
        return false;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.validatePin(pin);
        }
        return false;
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        if (account != null)
            commercialAccount.addAuthorizedUser(accountNumber, authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        if (account != null && authorizedPerson != null) {
            return commercialAccount.isAuthorizedUser(accountNumber, authorizedPerson);
        }
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        //TODO: review this implementation
        double totalConsumerAccountBalance = 0;
        int totalConsumerValues = 0;

        double totalCommercialAccountBalance = 0;
        int totalCommercialValues = 0;

        for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
            Long aLong = entry.getKey();
            Account account = entry.getValue();
            if (account.getAccountType() == AccountType.CONSUMER) {
                totalConsumerAccountBalance += account.getBalance();
                totalConsumerValues++;
            }
            if (account.getAccountType() == AccountType.COMMERCIAL) {
                totalCommercialAccountBalance += account.getBalance();
                totalCommercialValues++;
            }
        }

        double consumerAvg = totalConsumerAccountBalance / totalConsumerValues;

        double commercialAvg = totalCommercialAccountBalance / totalCommercialValues;

        Map<String, Double> avgAccountBalance = new HashMap<>();
        avgAccountBalance.put("ConsumerAccount", consumerAvg);
        avgAccountBalance.put("CommercialAccount", commercialAvg);

        return avgAccountBalance;
    }

    private Long previousAccountNumber()  {
        if (!accounts.isEmpty()) {
            return Long.valueOf(accounts.size());
        }
        return 0L;
    }
}
