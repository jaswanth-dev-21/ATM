public class AuthenticationService {

    private final Bank bank;

    public AuthenticationService(Bank bank) {
        this.bank = bank;
    }

    public Account login(
            String accountNumber,
            String pin) throws Exception {

        Account account = bank.findAccount(accountNumber);

        if (account == null) {
            throw new Exception("Account does not exist.");
        }

        if (account.isLocked()) {
            throw new Exception("Account is locked.");
        }

        if (account.verifyPin(pin)) {
            return account;
        }

        int attempts = account.getRemainingAttempts();

        if (account.isLocked()) {
            throw new Exception(
                    "Account locked after 3 failed attempts."
            );
        }

        throw new Exception(
                "Incorrect PIN. Attempts remaining: " + attempts
        );
    }
}