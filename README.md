# ATM Machine

A console-based **ATM Machine application built with Java**, designed to simulate common banking operations such as account creation, authentication, deposits, withdrawals, money transfers, PIN management, and transaction history.

The project follows a modular object-oriented design with separate components for accounts, authentication, banking operations, ATM services, and transaction management.

---

## Features

* 🔐 **Account Authentication**

  * Login using account number and PIN
  * PIN validation
  * Account locking after 3 failed login attempts

* 👤 **Account Management**

  * Create a new bank account
  * Automatically generate account numbers
  * Set an initial deposit

* 💰 **Banking Operations**

  * Check account balance
  * Deposit money
  * Withdraw money
  * Transfer money between accounts

* 📄 **Mini Statement**

  * View transaction history
  * Transaction date and time
  * Transaction type
  * Transaction amount
  * Transaction description
  * Balance after each transaction

* 🔑 **PIN Management**

  * Change existing PIN
  * Validate current PIN
  * Validate new 4-digit PIN

* 🧾 **Transaction Tracking**

  * Unique transaction IDs
  * Deposit transactions
  * Withdrawal transactions
  * Sent and received transfers
  * PIN change records

* ⚠️ **Input Validation**

  * Invalid amount handling
  * Negative amount prevention
  * Invalid PIN handling
  * Insufficient balance validation
  * Invalid account validation

---

## Application Flow

```text
                    ┌───────────────────┐
                    │     ATM Machine   │
                    └─────────┬─────────┘
                              │
                ┌─────────────┴─────────────┐
                │                           │
           Login                     Create Account
                │                           │
                ▼                           ▼
       AuthenticationService             Bank
                │                           │
                ▼                           ▼
             Account  ◄─────────────────────┘
                │
        ┌───────┼────────┬──────────┐
        │       │        │          │
        ▼       ▼        ▼          ▼
     Deposit Withdraw Transfer  Mini Statement
        │       │        │          │
        └───────┴────────┴──────────┘
                    │
                    ▼
              Transactions
```

---

## ATM Menu

After successful login, users can access:

```text
============== ATM MENU ==============

1. Check Balance
2. Deposit
3. Withdraw
4. Transfer Money
5. Mini Statement
6. Change PIN
7. Logout
```

---

## Project Structure

```text
ATM-Machine/
│
├── ATM/
│   ├── exceptions/
│   │
│   ├── ATM.java
│   ├── ATMService.java
│   ├── Account.java
│   ├── AuthenticationService.java
│   ├── Bank.java
│   ├── Main.java
│   ├── Transaction.java
│   └── TransactionType.java
│
├── .gitignore
└── Jenkinsfile
```

The main application entry point is `Main.java`, which creates an `ATM` instance and starts the application.

---

## Architecture

The application is divided into multiple responsibilities:

### ATM

Handles the main console interface and user interaction.

Responsibilities include:

* Login
* Account creation
* ATM menu
* Deposit
* Withdrawal
* Money transfer
* PIN change

### Account

Represents a customer's bank account.

It manages:

* Account number
* Customer name
* PIN
* Balance
* Login attempts
* Account locking
* Transactions

The account also validates deposits, withdrawals, transfers, and PIN changes.

### AuthenticationService

Handles user authentication and failed-login protection.

An account is locked after **3 unsuccessful PIN attempts**.

### Bank

Maintains the collection of accounts and provides account creation and account lookup functionality.

The application also contains sample accounts for testing.

### ATMService

Contains the main banking operations:

* Check balance
* Deposit
* Withdraw
* Transfer
* Mini statement
* Change PIN

It also generates unique transaction IDs.

### Transaction

Represents an individual transaction with:

* Transaction ID
* Transaction type
* Amount
* Description
* Timestamp
* Balance after transaction

---

## Transaction Types

The application supports:

```text
DEPOSIT
WITHDRAWAL
TRANSFER_SENT
TRANSFER_RECEIVED
PIN_CHANGE
```

---

## Sample Accounts

The application includes sample accounts for testing:

| Account Number | Name  | PIN    | Initial Balance |
| -------------- | ----- | ------ | --------------: |
| `10010001`     | Rahul | `1234` |         ₹10,000 |
| `10010002`     | Priya | `5678` |         ₹15,000 |

These accounts are initialized by the `Bank` class when the application starts.

> **Note:** These are demo credentials included in the source code for local testing. They should not be used in a real banking system.

---

## Requirements

* **Java JDK 8+**
* Java compiler (`javac`)
* Command-line terminal

---

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/jaswanth-dev-21/ATM.git
```

### 2. Navigate to the project

```bash
cd ATM/ATM-Machine/ATM
```

### 3. Compile the Java files

```bash
javac *.java
```

If your `exceptions` package is required during compilation, compile the source files together according to the package structure.

### 4. Run the application

```bash
java Main
```

---

## Usage

When the application starts, you will see:

```text
======================================
          WELCOME TO ATM
======================================
1. Login
2. Create New Account
3. Exit
======================================
Select option:
```

### Login

Use one of the sample accounts:

```text
Account Number: 10010001
PIN: 1234
```

or:

```text
Account Number: 10010002
PIN: 5678
```

### Create an Account

Select:

```text
2. Create New Account
```

Then provide:

* Full name
* 4-digit PIN
* Initial deposit

The application generates a unique account number.

---

## Security Features

The project includes basic authentication safeguards:

* 4-digit PIN validation
* Failed login attempt tracking
* Account lock after 3 failed attempts
* Current PIN verification before changing PIN
* Confirmation of the new PIN
* Prevention of transfers to the same account

These features are intended for educational simulation and are **not suitable for production banking systems**.

---

## Tech Stack

* **Java**
* Object-Oriented Programming
* Java Collections
* Exception Handling
* `LocalDateTime`
* UUID-based transaction IDs
* Console-based user interface

---

## OOP Concepts Demonstrated

This project demonstrates several core Java and OOP concepts:

* Classes and Objects
* Encapsulation
* Abstraction
* Enums
* Composition
* Collections
* Exception Handling
* Method Overloading/Organization
* Access Modifiers
* Separation of Responsibilities

---

## Future Improvements

Possible improvements include:

* 💾 Persistent database storage
* 🗄️ MySQL/PostgreSQL integration
* 🔒 Password/PIN hashing
* 🧑‍💼 Admin dashboard
* 🏦 Multiple bank branches
* 💳 Card number and ATM card simulation
* 📱 GUI application
* 🌐 REST API backend
* 🧪 Automated unit testing
* 📊 Transaction reporting
* 🔄 Persistent transaction history
* 🚀 CI/CD pipeline integration

---

## Project Status

**Status:** 🚧 Educational Project

This project is intended to demonstrate Java programming, object-oriented design, authentication logic, banking operations, and transaction management through a console-based ATM simulation.

---

## Author

**Jaswanth**

GitHub: [@jaswanth-dev-21](https://github.com/jaswanth-dev-21)

---

## License

This project is available for educational and personal use.
