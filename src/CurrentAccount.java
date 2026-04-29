public class CurrentAccount extends BankAccount {
    static final double MAINTENANCE_FEE=5.0;

    public CurrentAccount(String accountNumber, double balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }
    @Override
    public double withdrawMoney(double amount){
        if (amount + 1 > this.balance) {
            System.out.println("You don't have enough balance to withdraw.");
        }
        else{
            System.out.println("Doing withdraw...");
            this.balance -= amount;
        }
        return this.balance;
    }

}
