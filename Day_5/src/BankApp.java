import java.time.LocalDateTime;
import java.util.*;

class InsufficientFundsException extends Exception {
    InsufficientFundsException() {
        super("WARNING: " + "Insufficient Funds to proceed");
    }
}

class Transaction {
    private static String type;
    private static double amount;
    static LocalDateTime timeStamp;
    static String description;

    Transaction(String type, double amount, LocalDateTime timeStamp, String description) {
        Transaction.type = type;
        Transaction.amount = amount;
        Transaction.timeStamp = timeStamp;
        Transaction.description = description;
    }
    public static String toStrings() {
        return String.format("%s\t %s\t %s\t %s", type, amount, timeStamp, description);
    }

}

class BankAccount {
    public List <Transaction> history;
    //Transaction t1 = null;
    //Transaction t2 = null;
    private final String accountNumber;
    private double balance;
    private static final double minBalance = 1000.0;
    private static final double interestRate = 12.5;
    Random number = new Random();

    BankAccount() {
        history = new ArrayList<>();
        this.accountNumber = "D@NKME_ITZ" + (number.nextInt(1000, 100000) * 1000);
        balance = minBalance;
        //Transaction transaction = new Transaction("Created", balance, LocalDateTime.now(), "No deposit nor Withdraw!");
        history.add(new Transaction("Created", balance, LocalDateTime.now(), "No deposit nor Withdraw!"));
    }

    BankAccount( String accountNumberInput, double balance) {
        history = new ArrayList<>();
        if (!accountNumberInput.matches("D@NKME_ITZ0\\d+") || minBalance > balance) {
            throw new IllegalArgumentException("Invalid Account");
        }
        this.accountNumber = accountNumberInput;
        this.balance = balance = 1000.0;
    }

    void deposit(double amount) throws IllegalArgumentException {
        if (amount > 0) {
            this.balance += amount;
            //Transaction transaction = new Transaction("Deposit", amount, LocalDateTime.now(), "Deposited");
            history.add(new Transaction("Deposit", amount, LocalDateTime.now(), "Deposited"));
        } else {
            throw new IllegalArgumentException();
        }
        //System.out.println(amount+"\t deposited successfully.");
    }

    void withdraw(double amount) throws Exception {
        if (amount > 0 && this.balance - amount >= minBalance) {
            this.balance -= amount;
            //Transaction transaction = new Transaction("Withdraw", amount, LocalDateTime.now(), "Withdrawn");
            history.add(new Transaction("Withdraw", amount, LocalDateTime.now(), "Withdrawn"));
        }else{
            throw new InsufficientFundsException();}

    }

    double getBalance() {
        return this.balance;
    }

    String getAccountNo() {
        return this.accountNumber;
    }

    double calculateInterest() {
        return this.balance * (interestRate/100.0);
    }

    StringBuilder getTransactionHistory() {
        StringBuilder sb = new StringBuilder();
        history.sort(Comparator.comparing((Transaction t2) -> Transaction.timeStamp));
        for (Transaction transaction : history) {
            sb.append(transaction.toString()) ;
            sb.append("\n");
        }
        return sb;
    }

    public String toString(){
        return String.format("Account ID :%s\nCurrent Balance: %s", accountNumber, balance);
    }

}

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount obj = new BankAccount();
        String accountNumber = obj.getAccountNo();
        double balance = obj.getBalance();
        System.out.println("Welcome to My Bank Account App");
        System.out.println("------------------------------------");
        System.out.println("Your Account Number: " + accountNumber);
        System.out.println("Current Balance:(min.) " + balance);
        System.out.println("------------------------------------");
        BankAccount obj2 = new BankAccount(accountNumber, balance);
        //System.out.println("Enter the amount to deposit: ");
        double amount = 0.0;
        //obj.deposit(amount);
        boolean controllerOfTHeLoop = true;
        while (controllerOfTHeLoop) {
            System.out.println();
            System.out.print("Enter your choice (deposit, withdraw, interest, history, detailed, detailed_history,exit): ");
            String choice = input.next();
            System.out.println();
            switch (choice) {
                case "deposit":
                    System.out.print("Enter the amount to deposit: ");
                    amount = input.nextDouble();
                    obj2.deposit(amount);
                    break;

                case "withdraw":
                    try {
                        System.out.print("Enter the amount to withdraw: ");
                        amount = input.nextDouble();
                        obj2.withdraw(amount);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                case "interest":
                    double interest = obj2.calculateInterest();
                    obj2.deposit(interest);
                    break;
                case "history":
                    System.out.println(obj2.getTransactionHistory());
                    break;
                case "detailed_history":
                    String details = Transaction.toStrings();
                    System.out.println(details);break;
                case "detailed":
                    String detailed = obj2.toString();
                    System.out.println(detailed);break;
                case "exit":
                    controllerOfTHeLoop = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }


    }
}

