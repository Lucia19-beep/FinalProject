import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountList {
    List<BankAccount> accounts;

    public BankAccountList() {
        accounts = new ArrayList<>();
    }
    public  List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts( List<BankAccount> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(BankAccount ba){
        accounts.add(ba);

    }
    public BankAccount searchByAccountNumber(String accountNumber){
        for(BankAccount ba:accounts){
            if(ba.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return ba;
            }
        }
        return null;
    }

}
