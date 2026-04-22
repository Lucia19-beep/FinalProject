import java.time.LocalDate;

public class Customer extends User{
    int riskLevel;
    LocalDate linkingDate;

    public Customer(String name, String id, String address,int riskLevel,LocalDate linkingDate) {
        super(name, id, address);
        this.riskLevel=riskLevel;
        this.linkingDate=linkingDate;
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
}
