import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private final Map<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        // Optional sample accounts
        createSampleAccounts();
    }

    private void createSampleAccounts() {

        Account account1 = new Account(
                "10010001",
                "Rahul",
                "1234",
                10000
        );

        Account account2 = new Account(
                "10010002",
                "Priya",
                "5678",
                15000
        );

        accounts.put(
                account1.getAccountNumber(),
                account1
        );

        accounts.put(
                account2.getAccountNumber(),
                account2
        );
    }

    public Account findAccount(String accountNumber) {

        return accounts.get(accountNumber);
    }

    public boolean accountExists(String accountNumber) {

        return accounts.containsKey(accountNumber);
    }

    public Account createAccount(
            String customerName,
            String pin,
            double initialDeposit) {

        String accountNumber =
                generateAccountNumber();

        Account account = new Account(
                accountNumber,
                customerName,
                pin,
                initialDeposit
        );

        accounts.put(
                accountNumber,
                account
        );

        return account;
    }

    private String generateAccountNumber() {

        Random random = new Random();

        String accountNumber;

        do {

            accountNumber =
                    String.valueOf(
                            10000000 +
                            random.nextInt(90000000)
                    );

        } while (accounts.containsKey(accountNumber));

        return accountNumber;
    }
}