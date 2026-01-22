import java.util.*;

// Class representing a Bank Account
class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactions; // To store transaction history

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + balance);
    }

    public int getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount + " | New Balance: " + balance);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount + " | New Balance: " + balance);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void printTransactions() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        for(String t : transactions) {
            System.out.println(t);
        }
    }
}

// Class representing the Bank System
class BankSystem {
    private Map<Integer, BankAccount> accounts = new HashMap<>();

    public void createAccount(int accNo, String name, double initialBalance) {
        if(!accounts.containsKey(accNo)) {
            BankAccount account = new BankAccount(accNo, name, initialBalance);
            accounts.put(accNo, account);
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account number already exists!");
        }
    }

    public void deposit(int accNo, double amount) {
        BankAccount account = accounts.get(accNo);
        if(account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(int accNo, double amount) {
        BankAccount account = accounts.get(accNo);
        if(account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void viewBalance(int accNo) {
        BankAccount account = accounts.get(accNo);
        if(account != null) {
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void deleteAccount(int accNo) {
        if(accounts.remove(accNo) != null) {
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void viewTransactions(int accNo) {
        BankAccount account = accounts.get(accNo);
        if(account != null) {
            account.printTransactions();
        } else {
            System.out.println("Account not found!");
        }
    }
}

// Main Application
public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        while(true) {
            System.out.println("\n--- Bank Account Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. Delete Account");
            System.out.println("6. View Transactions");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    bank.createAccount(accNo, name, balance);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmt = sc.nextDouble();
                    bank.deposit(accNo, depositAmt);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Withdraw Amount: ");
                    double withdrawAmt = sc.nextDouble();
                    bank.withdraw(accNo, withdrawAmt);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.viewBalance(accNo);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.deleteAccount(accNo);
                    break;
                case 6:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.viewTransactions(accNo);
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}