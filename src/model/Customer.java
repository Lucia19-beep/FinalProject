package model;

import java.time.LocalDate;

public class Customer extends User{
    int riskLevel;
    LocalDate linkingDate;
    double balance;
    private String accountNumber;
    private BankAccount bankAccount;


    public Customer(String name, int id, String address, String password,
                    int riskLevel, LocalDate linkingDate, double balance,
                    String accountNumber) {
        super(name, id, address, password);
        this.riskLevel = riskLevel;
        this.linkingDate = linkingDate;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;

        if (bankAccount != null) {
            this.accountNumber = bankAccount.getAccountNumber();
            this.balance = bankAccount.getBalance();
        }
    }
    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDate getLinkingDate() {
        return linkingDate;
    }

    public void setLinkingDate(LocalDate linkingDate) {
        this.linkingDate = linkingDate;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String checkBalance() {
        return "Your balance is " + getBalance() + "€";
    }
    @Override
    public String toString() {
        return super.toString()+','+getRiskLevel()+','+getLinkingDate()+','+getBalance()+','+getAccountNumber();
    }
}
