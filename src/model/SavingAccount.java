package model;

public class SavingAccount extends BankAccount{
    double interestRate;

    public SavingAccount(String accountNumber, double balance, String accountHolder, double interestRate) {
        super(accountNumber, balance, accountHolder);
        this.interestRate = interestRate;
    }

    @Override
    public double withdrawMoney(double amount) {
        return 0;
    }
}
