import model.BankAccount;
import model.CurrentAccount;
import model.User;
import org.junit.jupiter.api.Test;
import service.BankAccountList;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountListTest {
    BankAccountList bankAccountList=new BankAccountList();
    @Test
    void getAccounts() {
        BankAccountList bankAccountList=new BankAccountList();
    }
    @Test
    void checkEquals(){
        CurrentAccount laura=new CurrentAccount("ES111",100000,"Laura");
        bankAccountList.addBankAccount(laura);
        bankAccountList.addBankAccount(new CurrentAccount("ES112",10530,"Amelia"));
        assertTrue(bankAccountList.getAccounts().contains(laura),"Laura's account should be on the list");
    }

    @Test
    void addAccount() {
        CurrentAccount laura=new CurrentAccount("ES111",100000,"Laura");
        bankAccountList.addBankAccount(laura);
        assertEquals(1, bankAccountList.getAccounts().size());
        assertEquals("Laura", bankAccountList.getAccounts().get(0).getAccountHolder());
    }
    @Test
    void deleteAccount(){
        BankAccount amelia=new CurrentAccount("ES112",100,"Amelia");
        bankAccountList.addBankAccount(amelia);

        bankAccountList.deleteBankAccount(new CurrentAccount("ES112",200,
                "Street 15"));
        assertNotNull(bankAccountList.getAccounts());
        assertEquals(0,bankAccountList.getAccounts().size());
    }
    @Test
    void searchByAccountNumber() {
        BankAccount laura=new CurrentAccount("ES112",270,"Laura");
        bankAccountList.addBankAccount(laura);

        BankAccount result = bankAccountList.searchByAccountNumber("ES112");

        assertNotNull(result);
        assertEquals("Laura", result.getAccountHolder());
    }

}