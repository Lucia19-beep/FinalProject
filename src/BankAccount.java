public abstract class BankAccount {
    String accountNumber;
    double balance;
    String accountHolder;

    public BankAccount(String accountNumber, double balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void transferMoney(int money,String accountNumber) {
        BankAccountList bankAccountList = new BankAccountList();

        if (balance >= money) {
            balance -= money;
            if(bankAccountList.searchByAccountNumber(accountNumber)!=null){
                BankAccount destinationAccount = bankAccountList.searchByAccountNumber(accountNumber);
                destinationAccount.setBalance(destinationAccount.getBalance() + money);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Destination account not found.");
            }
        } else {
            System.out.println("Insufficient funds for transfer.");
        }

    }
    public abstract double withdrawMoney(double amount);

}
