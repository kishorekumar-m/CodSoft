import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Amount withdrawn: $" + amount);
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.deposit(amount);
            System.out.println("Deposit successful. Amount deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + account.getBalance());
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);

        boolean exit = false;

        System.out.println("Welcome to the ATM!");
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Your choice please : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to be withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to be deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thanks for using the ATM. Goodbye! Have a Nice Day ;)");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
