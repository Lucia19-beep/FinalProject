package model;

import service.BankAccountList;

import java.util.Objects;

public abstract class BankAccount {
    String accountNumber;
    double balance;
    String accountHolder;
    private boolean locked;


    public BankAccount(String accountNumber, double balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.locked = false;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void transferMoney(int money,String accountNumber) {
        BankAccountList bankAccountList = new BankAccountList();

        if (balance >= money) {
            balance -= money;
            if(bankAccountList.searchByAccountNumber(accountNumber)!=null){
                BankAccount destinationAccount = bankAccountList.searchByAccountNumber(accountNumber);
                destinationAccount.setBalance(destinationAccount.getBalance() + money);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Destination account not found.");
            }
        } else {
            System.out.println("Insufficient funds for transfer.");
        }

    }
    public abstract double withdrawMoney(double amount);

    public void depositMoney(double amount) {
        if(amount>0){
            System.out.println("Doing income...");
            this.balance+=amount;
        }
        else{
            System.out.println("You can't deposit 0 or less money.");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount ba = (BankAccount) o;
        return Objects.equals(this.accountNumber, ba.accountNumber);
    }
}
