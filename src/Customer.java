import java.time.LocalDate;

public class Customer extends User{
    int riskLevel;
    LocalDate linkingDate;
    double balance;

    public Customer(String name, int id, String address,int riskLevel,LocalDate linkingDate,
                    double balance) {
        super(name, id, address);
        this.riskLevel=riskLevel;
        this.linkingDate=linkingDate;
        this.balance=balance;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDate getLinkingDate() {
        return linkingDate;
    }

    public void setLinkingDate(LocalDate linkingDate) {
        this.linkingDate = linkingDate;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void depositMoney(double quantityMoney){

    }
}
