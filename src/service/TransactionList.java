package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionList {
    private Map<String, List<Transaction>> historyTransactions;

    public TransactionList() {
        historyTransactions = new HashMap<>();
    }

    public Map<String, List<Transaction>> getTransactions() {
        return historyTransactions;
    }

    public void setTransactions(Map<String, List<Transaction>> historyTransactions) {
        this.historyTransactions = historyTransactions;
    }

    public void addTransaction(String accountNumber, Transaction transaction) {
        historyTransactions.putIfAbsent(accountNumber, new ArrayList<>());
        historyTransactions.get(accountNumber).add(transaction);
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return historyTransactions.getOrDefault(accountNumber, new ArrayList<>());
    }
    public void showAccountHistory(String accountNumber) {
        List<Transaction> accountMovements = getTransactionsByAccountNumber(accountNumber);

        if (accountMovements.isEmpty()) {
            System.out.println("No movements found for account: " + accountNumber);
        } else {
            for (Transaction transaction : accountMovements) {
                System.out.println(transaction);
            }
        }
    }

    public int getMaxTransactionId() {
        int max = 0;

        for (List<Transaction> transactions : historyTransactions.values()) {
            for (Transaction transaction : transactions) {
                if (transaction.getId() > max) {
                    max = transaction.getId();
                }
            }
        }

        return max;
    }

}
