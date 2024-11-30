import java.util.Calendar;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
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
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

abstract class BaseTransaction {
    private double amount;
    private Calendar date;
    private String transactionID;

    public BaseTransaction(double amount, Calendar date, String transactionID) {
        this.amount = amount;
        this.date = date;
        this.transactionID = transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public Calendar getDate() {
        return date;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public abstract void apply(BankAccount ba) throws InsufficientFundsException;

    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Date: " + date.getTime());
        System.out.println("Amount: " + amount);
    }
}

class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        if (ba.getBalance() < getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        ba.withdraw(getAmount());
        System.out.println("Withdrew: " + getAmount() + " from account: " + ba.getAccountNumber());
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 500.0);
        Calendar date = Calendar.getInstance();

        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1000.0, date, "TXN002");

        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        withdrawal.printTransactionDetails();
    }
}
