import model.Transaction;
import org.junit.jupiter.api.Test;
import service.TransactionList;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionListTest {
    TransactionList transactionList=new TransactionList();
    @Test
    void addTransaction() {
        Transaction transaction=new Transaction(1, "2026-05-19","Immediate"
                ,5000,"ES90172678109212562309");
        transactionList.addTransaction("ES90267810107921925623",transaction);
        assertEquals(1,transactionList.getTransactions(),"Transaction completed");
    }
    @Test
    void showAccountHistory() {
        Transaction transaction=new Transaction(1, "2026-05-19","Immediate"
                ,5000,"ES90172678109212562309");
        transactionList.addTransaction("ES90267810107921925623",transaction);
        transactionList.showAccountHistory("ES90267810107921925623");
    }

    @Test
    void getMaxTransactionId() {
        Transaction transaction=new Transaction(1, "2026-05-19","Immediate"
                ,5000,"ES90172678109212562309");
        transactionList.addTransaction("ES90267810107921925623",transaction);
        transactionList.getMaxTransactionId();
    }
}