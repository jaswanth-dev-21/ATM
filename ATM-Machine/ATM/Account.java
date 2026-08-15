import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String accountNumber;
    private final String customerName;

    private String pin;
    private double balance;

    private int failedLoginAttempts;
    private boolean locked;

    private final List<Transaction> transactions;

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public Account(
            String accountNumber,
            String customerName,
            String pin,
            double initialBalance) {

        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.pin = pin;
        this.balance = initialBalance;

        this.failedLoginAttempts = 0;
        this.locked = false;

        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean verifyPin(String enteredPin) {

        if (locked) {
            return false;
        }

        if (pin.equals(enteredPin)) {

            failedLoginAttempts = 0;

            return true;
        }

        failedLoginAttempts++;

        if (failedLoginAttempts >= MAX_LOGIN_ATTEMPTS) {
            locked = true;
        }

        return false;
    }

    public int getRemainingAttempts() {

        return MAX_LOGIN_ATTEMPTS -
                failedLoginAttempts;
    }

    public void deposit(
            double amount,
            String transactionId) {

        validateAmount(amount);

        balance += amount;

        transactions.add(
                new Transaction(
                        transactionId,
                        TransactionType.DEPOSIT,
                        amount,
                        "Cash deposit",
                        balance
                )
        );
    }

    public void withdraw(
            double amount,
            String transactionId)
            throws Exception {

        validateAmount(amount);

        if (amount > balance) {

            throw new Exception(
                    "Insufficient balance."
            );
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        transactionId,
                        TransactionType.WITHDRAWAL,
                        amount,
                        "Cash withdrawal",
                        balance
                )
        );
    }

    public void sendMoney(
            double amount,
            Account receiver,
            String transactionId)
            throws Exception {

        validateAmount(amount);

        if (amount > balance) {

            throw new Exception(
                    "Insufficient balance."
            );
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        transactionId,
                        TransactionType.TRANSFER_SENT,
                        amount,
                        "Transfer to " +
                                receiver.getAccountNumber(),
                        balance
                )
        );

        receiver.receiveMoney(
                amount,
                transactionId
        );
    }

    private void receiveMoney(
            double amount,
            String transactionId) {

        balance += amount;

        transactions.add(
                new Transaction(
                        transactionId,
                        TransactionType.TRANSFER_RECEIVED,
                        amount,
                        "Transfer received",
                        balance
                )
        );
    }

    public void changePin(
            String oldPin,
            String newPin,
            String transactionId)
            throws Exception {

        if (!pin.equals(oldPin)) {

            throw new Exception(
                    "Current PIN is incorrect."
            );
        }

        if (!isValidPin(newPin)) {

            throw new Exception(
                    "PIN must contain exactly 4 digits."
            );
        }

        pin = newPin;

        transactions.add(
                new Transaction(
                        transactionId,
                        TransactionType.PIN_CHANGE,
                        0,
                        "PIN changed",
                        balance
                )
        );
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private void validateAmount(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }
    }

    private boolean isValidPin(String pin) {

        return pin != null &&
                pin.matches("\\d{4}");
    }
}