import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String transactionId;
    private final TransactionType type;
    private final double amount;
    private final String description;
    private final LocalDateTime timestamp;
    private final double balanceAfterTransaction;

    public Transaction(
            String transactionId,
            TransactionType type,
            double amount,
            String description,
            double balanceAfterTransaction) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.balanceAfterTransaction =
                balanceAfterTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                );

        return String.format(
                "%-18s %-18s ₹%-10.2f %-25s ₹%.2f",
                timestamp.format(formatter),
                type,
                amount,
                description,
                balanceAfterTransaction
        );
    }
}