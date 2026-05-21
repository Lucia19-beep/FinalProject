package service;

import model.BankAccount;
import model.CurrentAccount;
import model.Customer;
import model.Transaction;

import java.time.LocalDate;
import java.util.List;

public class BankManagement {
    UserList usersList;
    BankAccountList accountsList;
    TransactionList transactionsList;
    private int nextTransactionId = 1;

    public BankManagement(){
        usersList=new UserList();
        accountsList=new BankAccountList();
        transactionsList=new TransactionList();
    }
    public UserList getUsersList() {
        return usersList;
    }
    public void setUsersList(UserList usersList) {
        this.usersList = usersList;
    }

    public BankAccountList getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(BankAccountList accountsList) {
        this.accountsList = accountsList;
    }
    public TransactionList getTransactionsList(){
        return transactionsList;
    }
    public void setTransactionsList(TransactionList transactionsList){
        this.transactionsList=transactionsList;
    }

    public void createCustomer(int id, String name, String address,String password,
                               int riskLevel, LocalDate linkingDate,double balance,String accountNumber){
        Customer customer=new Customer(name,id,address,password,riskLevel,linkingDate,balance,accountNumber);
        usersList.users.put(id,customer);
        System.out.println("model.Customer registered successfully");
    }
    public void doTransfer(String ibanOrigin, String ibanDestination, double amount, String concept) {
        BankAccount origin = accountsList.searchByAccountNumber(ibanOrigin);
        BankAccount destination = accountsList.searchByAccountNumber(ibanDestination);

        if (origin == null || destination == null) {
            System.out.println("Account not found.");
        } else if (origin.isLocked() || destination.isLocked()) {
            System.out.println("One of the accounts is locked.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (origin.getBalance() < amount) {
            System.out.println("Insufficient funds.");
        } else {
            origin.withdrawMoney(amount);
            destination.depositMoney(amount);

            Transaction transaction = new Transaction(
                    generateTransactionId(),
                    LocalDate.now().toString(),
                    "Transfer - " + concept,
                    amount,
                    ibanOrigin
            );

            transactionsList.addTransaction(ibanOrigin, transaction);

            System.out.println("Transfer successful.");
        }
    }
    public boolean isAccountOwner(String id,String accountNumber){
        BankAccount bankAccount=accountsList.searchByAccountNumber(accountNumber);
        boolean isValid=false;

        if(bankAccount!=null && id!=null && bankAccount.getAccountHolder().equalsIgnoreCase(id)){
            isValid=true;
        }
        return isValid;
    }
    public void viewLastMovements(String id,String accountNumber){
        BankAccount bankAccount=accountsList.searchByAccountNumber(accountNumber);

        if(bankAccount!=null){
            if(isAccountOwner(id,accountNumber)){
                List<Transaction> transactions=transactionsList.getTransactionsByAccountNumber(accountNumber);
                System.out.println("Last movements for account "+accountNumber+":");
                for(Transaction transaction:transactions){
                    System.out.println(transaction);
                }
            } else {
                System.out.println("You are not the owner of this account.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    public void depositMoney(String accountNumber, double amount,String date) {
        BankAccount bankAccount = accountsList.searchByAccountNumber(accountNumber);

        if (bankAccount != null) {
            bankAccount.depositMoney(amount);

            Transaction transaction = new Transaction(
                    generateTransactionId(),
                    date,
                    "Deposit",
                  amount,
                    accountNumber
            );

            transactionsList.addTransaction(accountNumber, transaction);

        } else {
            System.out.println("Account not found.");
        }
    }
     public void withdrawMoney(String accountNumber,double amount,String date){
        BankAccount bankAccount=accountsList.searchByAccountNumber(accountNumber);

        if(bankAccount!=null){
            bankAccount.withdrawMoney(amount);
            Transaction transaction=new Transaction(generateTransactionId(),date,"Withdraw",amount,accountNumber);
            transactionsList.addTransaction(accountNumber,transaction);
        } else {
            System.out.println("Account not found.");
        }
    }
    private int generateTransactionId() {
        return nextTransactionId++;
    }

}
