package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    int id;
    String date;
    String type;
    double amount;
    String accountDestination;

    public Transaction(int id, String date, String type, double amount, String accountDestination) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.accountDestination = accountDestination;
    }
    public int getId() {
        return id;
    }

    public String getDate() {
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
        return date + "-" + type + "-" + amount +"€";
    }
}
