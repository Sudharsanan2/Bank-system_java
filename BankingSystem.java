import java.util.HashMap;
import java.util.Map;

// Class representing a single bank account
class Account {
    private String accountNumber;
    private String customerName;
    private double balance;

    public Account(String accountNumber, String customerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = initialBalance;
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

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}

// Class managing multiple accounts
class Bank {
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    // Adds a new account to the bank
    public void addAccount(String accountNumber, String customerName, double initialBalance) {
        if (!accountMap.containsKey(accountNumber)) {
            Account newAccount = new Account(accountNumber, customerName, initialBalance);
            accountMap.put(accountNumber, newAccount);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    // Deposits amount to an account
    public void depositToAccount(String accountNumber, double amount) {
        Account account = accountMap.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Displays information of an account
    public void displayAccountInfo(String accountNumber) {
        Account account = accountMap.get(accountNumber);
        if (account != null) {
            System.out.println("\n--- Account Information ---");
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Customer Name  : " + account.getCustomerName());
            System.out.println("Current Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}

// Main class to demonstrate the banking system
public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Adding accounts
        bank.addAccount("101", "John Doe", 1000.0);
        bank.addAccount("102", "Jane Smith", 1500.0);

        // Performing transactions
        bank.depositToAccount("101", 500.0);   // Valid
        bank.depositToAccount("103", 200.0);   // Invalid (non-existent account)

        // Displaying account information
        bank.displayAccountInfo("101");
        bank.displayAccountInfo("102");
    }
}
