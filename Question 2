import java.util.Calendar;

interface TransactionInterface {
    double getAmount();
    Calendar getDate();
    String getTransactionID();
    void printTransactionDetails();
    void apply(BankAccount ba);
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
        System.out.println("Deposited: " + getAmount() + " to account: " + ba.getAccountNumber());
    }
}

class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        if (ba.getBalance() >= getAmount()) {
            ba.withdraw(getAmount());
            System.out.println("Withdrew: " + getAmount() + " from account: " + ba.getAccountNumber());
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    public boolean reverse(BankAccount ba) {
        ba.deposit(getAmount());
        System.out.println("Reversed withdrawal of: " + getAmount());
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.0);
        Calendar date = Calendar.getInstance();

        DepositTransaction deposit = new DepositTransaction(500.0, date, "TXN001");
        deposit.apply(account);
        deposit.printTransactionDetails();

        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1200.0, date, "TXN002");
        withdrawal.apply(account);
        withdrawal.reverse(account);
    }
}
