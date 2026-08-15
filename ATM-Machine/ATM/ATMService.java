import java.util.UUID;

public class ATMService {

    public void checkBalance(Account account) {

        System.out.println();

        System.out.println(
                "Available Balance: ₹" +
                String.format("%.2f", account.getBalance())
        );
    }

    public void deposit(
            Account account,
            double amount) {

        String transactionId = generateTransactionId();

        account.deposit(
                amount,
                transactionId
        );

        System.out.println();
        System.out.println("Deposit successful.");

        System.out.println(
                "Transaction ID: " + transactionId
        );

        System.out.println(
                "New Balance: ₹" +
                String.format(
                        "%.2f",
                        account.getBalance()
                )
        );
    }

    public void withdraw(
            Account account,
            double amount) throws Exception {

        String transactionId = generateTransactionId();

        account.withdraw(
                amount,
                transactionId
        );

        System.out.println();
        System.out.println(
                "Please collect your cash."
        );

        System.out.println(
                "Transaction ID: " + transactionId
        );

        System.out.println(
                "Remaining Balance: ₹" +
                String.format(
                        "%.2f",
                        account.getBalance()
                )
        );
    }

    public void transfer(
            Account sender,
            Account receiver,
            double amount) throws Exception {

        String transactionId = generateTransactionId();

        sender.sendMoney(
                amount,
                receiver,
                transactionId
        );

        System.out.println();
        System.out.println("Transfer successful.");

        System.out.println(
                "Transaction ID: " + transactionId
        );

        System.out.println(
                "Transferred: ₹" +
                String.format("%.2f", amount)
        );

        System.out.println(
                "Remaining Balance: ₹" +
                String.format(
                        "%.2f",
                        sender.getBalance()
                )
        );
    }

    public void showStatement(Account account) {

        System.out.println();

        System.out.println(
                "================ MINI STATEMENT ================"
        );

        System.out.printf(
                "%-18s %-18s %-12s %-25s %s%n",
                "Date",
                "Type",
                "Amount",
                "Description",
                "Balance"
        );

        System.out.println(
                "---------------------------------------------------------------"
        );

        if (account.getTransactions().isEmpty()) {

            System.out.println(
                    "No transactions available."
            );

        } else {

            for (Transaction transaction :
                    account.getTransactions()) {

                System.out.println(transaction);
            }
        }

        System.out.println(
                "==============================================================="
        );

        System.out.println(
                "Current Balance: ₹" +
                String.format(
                        "%.2f",
                        account.getBalance()
                )
        );
    }

    public void changePin(
            Account account,
            String oldPin,
            String newPin) throws Exception {

        String transactionId = generateTransactionId();

        account.changePin(
                oldPin,
                newPin,
                transactionId
        );

        System.out.println(
                "PIN changed successfully."
        );
    }

    private String generateTransactionId() {

        return "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}