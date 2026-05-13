import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankManagement {
    UserList usersList;
    BankAccountList accountsList;
    TransactionList transactionsList;

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

    public void createCustomer(int id, String name, String address, int riskLevel
            , LocalDate linkingDate,double balance){
        Customer customer=new Customer(name,id,address,riskLevel,linkingDate,balance);
        usersList.users.put(id,customer);
        System.out.println("Customer registered successfully");
    }
    public void doTransfer(String ibanOrigin,String ibanDestination,double amount
            ,String concept){
        CurrentAccount origin= (CurrentAccount) accountsList.accounts.get
                (Integer.parseInt(ibanOrigin));
        CurrentAccount destination= (CurrentAccount) accountsList.accounts.get
                (Integer.parseInt(ibanDestination));

        if(origin!=null && destination!=null){
            if(origin.getBalance()>=amount){
                origin.withdrawMoney(amount);
                destination.depositMoney(amount);
            }
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
        }

    }

}
