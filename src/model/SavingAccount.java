package model;

public class SavingAccount extends BankAccount{
    double interestRate;

    public SavingAccount(String accountNumber, double balance, String accountHolder, double interestRate) {
        super(accountNumber, balance, accountHolder);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double withdrawMoney(double amount) {
        if (isLocked()) {
            System.out.println("Account is locked.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > getBalance()) {
            System.out.println("You don't have enough balance to withdraw.");
        } else {
            setBalance(getBalance() - amount);
            System.out.println("Withdraw successful.");
        }

        return getBalance();
    }
}
