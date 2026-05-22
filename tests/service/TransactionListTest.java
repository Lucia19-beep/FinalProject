package service;

import model.Transaction;
import org.junit.jupiter.api.Test;
import service.TransactionList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionListTest {

    @Test
    void addTransactionAddsTransactionToAccount() {
        TransactionList list = new TransactionList();
        Transaction transaction = new Transaction(1, "2026-05-19", "Deposit", 500.0, "ES001");

        list.addTransaction("ES001", transaction);

        assertEquals(1, list.getTransactionsByAccountNumber("ES001").size());
        assertEquals(transaction, list.getTransactionsByAccountNumber("ES001").get(0));
    }

    @Test
    void getTransactionsByAccountNumberReturnsEmptyListIfAccountHasNoTransactions() {
        TransactionList list = new TransactionList();

        List<Transaction> result = list.getTransactionsByAccountNumber("ES999");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getMaxTransactionIdReturnsHighestId() {
        TransactionList list = new TransactionList();

        list.addTransaction("ES001", new Transaction(1, "2026-05-19", "Deposit", 100.0, "ES001"));
        list.addTransaction("ES001", new Transaction(5, "2026-05-19", "Withdraw", 50.0, "ES001"));
        list.addTransaction("ES002", new Transaction(3, "2026-05-19", "Deposit", 200.0, "ES002"));

        assertEquals(5, list.getMaxTransactionId());
    }

    @Test
    void getMaxTransactionIdReturnsZeroWhenEmpty() {
        TransactionList list = new TransactionList();

        assertEquals(0, list.getMaxTransactionId());
    }
}