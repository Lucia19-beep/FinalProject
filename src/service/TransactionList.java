package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionList {
    Map<String,List<Transaction>> historyTransactions;

    public TransactionList(){
        historyTransactions=new HashMap<>();
    }
    public Map<String,List<Transaction>> getTransactions() {
        return historyTransactions;
    }

    public void setTransactions(Map<String,List<Transaction>> historyTransactions) {
        this.historyTransactions=historyTransactions;
    }
    public void addTransaction(String accountNumber, Transaction t) {
        if (!historyTransactions.containsKey(accountNumber)) {
            historyTransactions.put(accountNumber, new ArrayList<>());
        }
        historyTransactions.get(accountNumber).add(t);
    }

    public void showAccountHistory(String accountNumber) {
        List<Transaction> accountMovements = historyTransactions.get(accountNumber);

        if (accountMovements == null || accountMovements.isEmpty()) {
            System.out.println("No movements found for account: " + accountNumber);
        } else {
            for (Transaction t : accountMovements) {
                System.out.println(t.toString());
            }
        }
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return historyTransactions.getOrDefault(accountNumber, new ArrayList<>());
    }

}
