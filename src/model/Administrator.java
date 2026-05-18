package model;

import service.TransactionList;

import java.util.List;
import java.util.Map;

public class Administrator extends User{
    int accessLevel;

    public Administrator(String name, int id, String address, String password, int accessLevel) {
        super(name, id, address, password);
        this.accessLevel = accessLevel;
    }
    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    public void showTransactions() {

        TransactionList transactionList = new TransactionList();

        Map<String, List<Transaction>> transactions =
                transactionList.getTransactions();

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");

        } else {

            System.out.println("==== TRANSACTIONS ====");

            for (String accountNumber : transactions.keySet()) {

                System.out.println("Account: " + accountNumber);

                List<Transaction> accountTransactions =
                        transactions.get(accountNumber);

                for (Transaction transaction : accountTransactions) {

                    System.out.println(transaction);
                }

                System.out.println();
            }
        }
    }
    @Override
    public String toString() {
        return super.toString()+','+getAccessLevel();
    }
}
