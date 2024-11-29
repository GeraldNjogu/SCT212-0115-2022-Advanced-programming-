import java.util.Calendar;

interface TransactionInterface {
    double getAmount();
    Calendar getDate();
    String getTransactionID();
    void printTransactionDetails();
    void apply(BankAccount ba) throws Exception;
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + " | New Balance: " + balance);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

abstract class BaseTransaction implements TransactionInterface {
    private double amount;
    private Calendar date;
    private String transactionID;

    public BaseTransaction(double amount, Calendar date, String transactionID) {
        this.amount = amount;
        this.date = date;
        this.transactionID = transactionID;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Calendar getDate() {
        return date;
    }

    @Override
    public String getTransactionID() {
        return transactionID;
    }

    @Override
    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Date: " + date.getTime());
        System.out.println("Amount: " + amount);
    }
}

class DepositTransaction extends BaseTransaction {
    public DepositTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        ba.deposit(getAmount());
        System.out.println("Deposit transaction applied.");
    }
}

class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        ba.withdraw(getAmount());
        System.out.println("Withdrawal transaction applied.");
    }

    public boolean reverse(BankAccount ba) {
        ba.deposit(getAmount());
        System.out.println("Withdrawal transaction reversed.");
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.0);
        Calendar date = Calendar.getInstance();

        // Testing DepositTransaction
        System.out.println("=== Deposit Transaction ===");
        BaseTransaction deposit = new DepositTransaction(200.0, date, "TXN001");
        deposit.printTransactionDetails();
        deposit.apply(account);

        // Testing WithdrawalTransaction
        System.out.println("\n=== Withdrawal Transaction ===");
        BaseTransaction withdrawal = new WithdrawalTransaction(1200.0, date, "TXN002");
        withdrawal.printTransactionDetails();
        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Testing Polymorphism
        System.out.println("\n=== Polymorphism Test ===");
        BaseTransaction polymorphicTransaction = new DepositTransaction(300.0, date, "TXN003");
        polymorphicTransaction.apply(account); // Using BaseTransaction reference

        // Testing Reversal of WithdrawalTransaction
        System.out.println("\n=== Reversal Test ===");
        WithdrawalTransaction reverseTransaction = new WithdrawalTransaction(200.0, date, "TXN004");
        try {
            reverseTransaction.apply(account);
            reverseTransaction.reverse(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception during reversal: " + e.getMessage());
        }
    }
}
