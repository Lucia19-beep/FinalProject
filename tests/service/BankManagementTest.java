package service;

import model.BankAccount;
import model.CurrentAccount;
import org.junit.jupiter.api.Test;
import service.BankManagement;

import static org.junit.jupiter.api.Assertions.*;

class BankManagementTest {

    @Test
    void depositMoneyIncreasesAccountBalanceAndCreatesTransaction() {
        BankManagement bank = new BankManagement();
        BankAccount account = new CurrentAccount("ES001", 100.0, "1");

        bank.getAccountsList().addBankAccount(account);

        bank.depositMoney("ES001", 50.0, "2026-05-19");

        assertEquals(150.0, account.getBalance(), 0.01);
        assertEquals(1, bank.getTransactionsList().getTransactionsByAccountNumber("ES001").size());
    }

    @Test
    void withdrawMoneyReducesAccountBalanceAndCreatesTransaction() {
        BankManagement bank = new BankManagement();
        BankAccount account = new CurrentAccount("ES001", 100.0, "1");

        bank.getAccountsList().addBankAccount(account);

        bank.withdrawMoney("ES001", 40.0, "2026-05-19");

        assertEquals(60.0, account.getBalance(), 0.01);
        assertEquals(1, bank.getTransactionsList().getTransactionsByAccountNumber("ES001").size());
    }

    @Test
    void doTransferMovesMoneyBetweenAccounts() {
        BankManagement bank = new BankManagement();
        BankAccount origin = new CurrentAccount("ES001", 100.0, "1");
        BankAccount destination = new CurrentAccount("ES002", 50.0, "2");

        bank.getAccountsList().addBankAccount(origin);
        bank.getAccountsList().addBankAccount(destination);

        bank.doTransfer("ES001", "ES002", 30.0, "Test");

        assertEquals(70.0, origin.getBalance(), 0.01);
        assertEquals(80.0, destination.getBalance(), 0.01);
        assertEquals(1, bank.getTransactionsList().getTransactionsByAccountNumber("ES001").size());
    }

    @Test
    void doTransferDoesNothingIfOriginHasNotEnoughMoney() {
        BankManagement bank = new BankManagement();
        BankAccount origin = new CurrentAccount("ES001", 20.0, "1");
        BankAccount destination = new CurrentAccount("ES002", 50.0, "2");

        bank.getAccountsList().addBankAccount(origin);
        bank.getAccountsList().addBankAccount(destination);

        bank.doTransfer("ES001", "ES002", 100.0, "Test");

        assertEquals(20.0, origin.getBalance(), 0.01);
        assertEquals(50.0, destination.getBalance(), 0.01);
        assertEquals(0, bank.getTransactionsList().getTransactionsByAccountNumber("ES001").size());
    }

    @Test
    void doTransferDoesNothingIfOneAccountIsLocked() {
        BankManagement bank = new BankManagement();
        BankAccount origin = new CurrentAccount("ES001", 100.0, "1");
        BankAccount destination = new CurrentAccount("ES002", 50.0, "2");

        origin.setLocked(true);

        bank.getAccountsList().addBankAccount(origin);
        bank.getAccountsList().addBankAccount(destination);

        bank.doTransfer("ES001", "ES002", 30.0, "Test");

        assertEquals(100.0, origin.getBalance(), 0.01);
        assertEquals(50.0, destination.getBalance(), 0.01);
    }

    @Test
    void isAccountOwnerReturnsTrueWhenIdMatchesAccountHolder() {
        BankManagement bank = new BankManagement();
        BankAccount account = new CurrentAccount("ES001", 100.0, "1");

        bank.getAccountsList().addBankAccount(account);

        assertTrue(bank.isAccountOwner("1", "ES001"));
    }

    @Test
    void isAccountOwnerReturnsFalseWhenIdDoesNotMatchAccountHolder() {
        BankManagement bank = new BankManagement();
        BankAccount account = new CurrentAccount("ES001", 100.0, "1");

        bank.getAccountsList().addBankAccount(account);

        assertFalse(bank.isAccountOwner("2", "ES001"));
    }
}