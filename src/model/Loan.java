package model;

import java.time.LocalDate;

public class Loan {
    String loanId;
    double amount;
    int months;
    String customerId;
    LocalDate startDate;
    LocalDate endDate;
    boolean approved;


    public Loan(String loanId, double amount, int months, String customerId, LocalDate startDate, LocalDate endDate) {
        this.loanId = loanId;
        this.amount = amount;
        this.months = months;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = false;
    }
}