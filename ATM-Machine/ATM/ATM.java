import java.util.Scanner;

public class ATM {

    private final Scanner scanner;
    private final Bank bank;
    private final AuthenticationService authenticationService;
    private final ATMService atmService;

    public ATM() {
        scanner = new Scanner(System.in);
        bank = new Bank();
        authenticationService = new AuthenticationService(bank);
        atmService = new ATMService();
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("          WELCOME TO ATM");
            System.out.println("======================================");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.println("======================================");

            System.out.print("Select option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    Account account = login();

                    if (account != null) {
                        showMenu(account);
                    }

                    break;

                case "2":

                    createAccount();

                    break;

                case "3":

                    running = false;

                    System.out.println();
                    System.out.println("Thank you for using our ATM.");

                    break;

                default:

                    System.out.println();
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // ================= LOGIN =================

    private Account login() {

        System.out.println();
        System.out.println("============== LOGIN ==============");

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        try {

            Account account =
                    authenticationService.login(accountNumber, pin);

            System.out.println();
            System.out.println("Login successful.");
            System.out.println("Welcome, " +
                    account.getCustomerName() + "!");

            return account;

        } catch (Exception e) {

            System.out.println();
            System.out.println("ERROR: " + e.getMessage());

            return null;
        }
    }

    // ================= CREATE ACCOUNT =================

    private void createAccount() {

        System.out.println();
        System.out.println("========== CREATE NEW ACCOUNT ==========");

        System.out.print("Enter your full name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {

            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();

        if (!pin.matches("\\d{4}")) {

            System.out.println();
            System.out.println(
                    "PIN must contain exactly 4 digits."
            );

            return;
        }

        System.out.print("Enter initial deposit: ₹");
        String depositInput = scanner.nextLine();

        double initialDeposit;

        try {

            initialDeposit =
                    Double.parseDouble(depositInput);

        } catch (NumberFormatException e) {

            System.out.println();
            System.out.println("Invalid amount.");

            return;
        }

        if (initialDeposit < 0) {

            System.out.println();
            System.out.println(
                    "Initial deposit cannot be negative."
            );

            return;
        }

        Account account =
                bank.createAccount(
                        name,
                        pin,
                        initialDeposit
                );

        System.out.println();
        System.out.println("======================================");
        System.out.println("    ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("======================================");
        System.out.println(
                "Customer Name: " +
                        account.getCustomerName()
        );
        System.out.println(
                "Account Number: " +
                        account.getAccountNumber()
        );
        System.out.println(
                "Initial Balance: ₹" +
                        String.format(
                                "%.2f",
                                account.getBalance()
                        )
        );
        System.out.println("======================================");
        System.out.println(
                "Please remember your account number."
        );
    }

    // ================= ATM MENU =================

    private void showMenu(Account account) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println();
            System.out.println("============== ATM MENU ==============");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Mini Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. Logout");
            System.out.println("======================================");

            System.out.print("Select option: ");

            String choice = scanner.nextLine();

            try {

                switch (choice) {

                    case "1":

                        atmService.checkBalance(account);

                        break;

                    case "2":

                        deposit(account);

                        break;

                    case "3":

                        withdraw(account);

                        break;

                    case "4":

                        transfer(account);

                        break;

                    case "5":

                        atmService.showStatement(account);

                        break;

                    case "6":

                        changePin(account);

                        break;

                    case "7":

                        loggedIn = false;

                        System.out.println();
                        System.out.println(
                                "You have been logged out."
                        );

                        break;

                    default:

                        System.out.println();
                        System.out.println("Invalid option.");
                }

            } catch (Exception e) {

                System.out.println();
                System.out.println(
                        "ERROR: " + e.getMessage()
                );
            }
        }
    }

    // ================= DEPOSIT =================

    private void deposit(Account account) {

        System.out.print("Enter deposit amount: ₹");

        String input = scanner.nextLine();

        try {

            double amount =
                    Double.parseDouble(input);

            atmService.deposit(account, amount);

        } catch (NumberFormatException e) {

            System.out.println();
            System.out.println(
                    "Invalid amount. Please enter a number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println();
            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }

    // ================= WITHDRAW =================

    private void withdraw(Account account) {

        System.out.print("Enter withdrawal amount: ₹");

        String input = scanner.nextLine();

        try {

            double amount =
                    Double.parseDouble(input);

            atmService.withdraw(account, amount);

        } catch (NumberFormatException e) {

            System.out.println();
            System.out.println(
                    "Invalid amount. Please enter a number."
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }

    // ================= TRANSFER =================

    private void transfer(Account account) {

        System.out.print(
                "Enter receiver account number: "
        );

        String receiverNumber =
                scanner.nextLine();

        Account receiver =
                bank.findAccount(receiverNumber);

        if (receiver == null) {

            System.out.println();
            System.out.println(
                    "ERROR: Receiver account does not exist."
            );

            return;
        }

        if (receiver.getAccountNumber()
                .equals(account.getAccountNumber())) {

            System.out.println();
            System.out.println(
                    "ERROR: You cannot transfer money to yourself."
            );

            return;
        }

        System.out.print("Enter amount: ₹");

        String input = scanner.nextLine();

        try {

            double amount =
                    Double.parseDouble(input);

            atmService.transfer(
                    account,
                    receiver,
                    amount
            );

        } catch (NumberFormatException e) {

            System.out.println();
            System.out.println(
                    "Invalid amount. Please enter a number."
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }

    // ================= CHANGE PIN =================

    private void changePin(Account account) {

        System.out.println();
        System.out.println("========== CHANGE PIN ==========");

        System.out.print("Enter current PIN: ");
        String oldPin = scanner.nextLine();

        System.out.print("Enter new 4-digit PIN: ");
        String newPin = scanner.nextLine();

        System.out.print("Confirm new PIN: ");
        String confirmPin = scanner.nextLine();

        if (!newPin.equals(confirmPin)) {

            System.out.println();
            System.out.println(
                    "ERROR: New PIN and confirmation PIN do not match."
            );

            return;
        }

        try {

            atmService.changePin(
                    account,
                    oldPin,
                    newPin
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }
}