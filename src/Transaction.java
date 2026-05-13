import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    int id;
    LocalDateTime date;
    String type;
    double amount;
    String accountDestination;

    public Transaction(int id, LocalDateTime date, String type, double amount, String accountDestination) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.accountDestination = accountDestination;
    }
    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountDestination() {
        return accountDestination;
    }
    @Override
    public String toString(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("DD-MM-YYYY");
        String dateFormatted=date.format(dtf);
        return dateFormatted + "-" + type + "-" + amount +"€";
    }
}
