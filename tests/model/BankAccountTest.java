package model;

import model.CurrentAccount;
import model.SavingAccount;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void depositPositiveMoneyIncreasesBalance() {
        CurrentAccount account = new CurrentAccount("ES001", 100.0, "Pepe");

        account.depositMoney(50.0);

        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    void depositNegativeMoneyDoesNotChangeBalance() {
        CurrentAccount account = new CurrentAccount("ES001", 100.0, "Pepe");

        account.depositMoney(-50.0);

        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    void withdrawMoneyReducesBalance() {
        CurrentAccount account = new CurrentAccount("ES001", 100.0, "Pepe");

        account.withdrawMoney(40.0);

        assertEquals(60.0, account.getBalance(), 0.01);
    }

    @Test
    void withdrawMoreThanBalanceDoesNotChangeBalance() {
        CurrentAccount account = new CurrentAccount("ES001", 100.0, "Pepe");

        account.withdrawMoney(200.0);

        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    void lockedSavingAccountCannotWithdrawMoney() {
        SavingAccount account = new SavingAccount("ES002", 100.0, "Laura", 2.5);
        account.setLocked(true);

        account.withdrawMoney(50.0);

        assertEquals(100.0, account.getBalance(), 0.01);
    }
}