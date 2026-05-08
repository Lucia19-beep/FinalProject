public class CurrentAccount extends BankAccount {
    static final double MAINTENANCE_FEE=5.0;

    public CurrentAccount(String accountNumber, double balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }
    @Override
    public double withdrawMoney(double amount){
        if (amount > this.balance) {
            System.out.println("You don't have enough balance to withdraw.");
        }
        else{
            System.out.println("Doing withdraw...");
            this.balance -= amount;
        }
        return this.balance;
    }
    public double depositMoney(double amount){
        if(amount>0){
            System.out.println("Doing income...");
            this.balance+=amount;
        }
        else{
            System.out.println("You can't deposit 0 or less money.");
        }
        return this.balance;
    }

}
