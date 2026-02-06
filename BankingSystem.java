import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String accNo, String name, double balance) {
        if (!accounts.containsKey(accNo)) {
            accounts.put(accNo, new Account(accNo, name, balance));
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }
    }

    public void deposit(String accNo, double amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            acc.deposit(amount);
            System.out.println("Deposit successful. Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accNo, double amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            acc.withdraw(amount);
            System.out.println("Current balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String from, String to, double amount) {
        Account sender = accounts.get(from);
        Account receiver = accounts.get(to);

        if (sender != null && receiver != null) {
            if (amount > 0 && sender.getBalance() >= amount) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public void displayAccount(String accNo) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.println("\n--- Account Details ---");
            System.out.println("Account Number : " + acc.getAccountNumber());
            System.out.println("Customer Name  : " + acc.getCustomerName());
            System.out.println("Balance        : " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Account");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNo = sc.next();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.next();
                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();
                    bank.createAccount(accNo, name, bal);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.next();
                    System.out.print("Enter Deposit Amount: ");
                    double dep = sc.nextDouble();
                    bank.deposit(accNo, dep);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.next();
                    System.out.print("Enter Withdrawal Amount: ");
                    double with = sc.nextDouble();
                    bank.withdraw(accNo, with);
                    break;

                case 4:
                    System.out.print("From Account: ");
                    String from = sc.next();
                    System.out.print("To Account: ");
                    String to = sc.next();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    bank.transfer(from, to, amt);
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.next();
                    bank.displayAccount(accNo);
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}

