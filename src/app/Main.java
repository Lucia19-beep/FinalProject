package app;

import model.*;
import service.BankManagement;
import service.Login;

import java.util.Scanner;

public class Main {
    public static void employeeLogin(Scanner sc, User cs){
        boolean exit=false;
        while(!exit){
            System.out.println("====EMPLOYEE====");
            System.out.println("1. Approve a loan");
            System.out.println("2. Log out");
            String choice=sc.nextLine();

            switch(choice){
                case "1":
                    //TODO function that allows employee to approve a loan
                    break;
                case "2":
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    public static void adminLogin(Scanner sc, Administrator cs) {

        BankManagement bankManagement = new BankManagement();

        boolean exit = false;

        while (!exit) {

            System.out.println("====ADMIN====");
            System.out.println("1. View recent transactions");
            System.out.println("2. Lock account");
            System.out.println("3. Unlock account");
            System.out.println("4. Log out");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":

                    cs.showTransactions();

                    break;

                case "2":

                    System.out.println("Enter account number to lock:");

                    String lockAccountNumber = sc.nextLine();

                    BankAccount lockAccount =
                            bankManagement.getAccountsList()
                                    .searchByAccountNumber(lockAccountNumber);

                    if (lockAccount != null) {

                        if (lockAccount instanceof CurrentAccount current) {

                            current.setLocked(true);

                            System.out.println("Account locked successfully.");
                        }

                        else if (lockAccount instanceof SavingAccount saving) {

                            saving.setLocked(true);

                            System.out.println("Account locked successfully.");
                        }

                    } else {

                        System.out.println("Account not found.");
                    }

                    break;

                case "3":

                    System.out.println("Enter account number to unlock:");

                    String unlockAccountNumber = sc.nextLine();

                    BankAccount unlockAccount =
                            bankManagement.getAccountsList()
                                    .searchByAccountNumber(unlockAccountNumber);

                    if (unlockAccount != null) {

                        if (unlockAccount instanceof CurrentAccount current) {

                            current.setLocked(false);

                            System.out.println("Account unlocked successfully.");
                        }

                        else if (unlockAccount instanceof SavingAccount saving) {

                            saving.setLocked(false);

                            System.out.println("Account unlocked successfully.");
                        }

                    } else {

                        System.out.println("Account not found.");
                    }

                    break;

                case "4":

                    exit = true;

                    break;

                default:

                    System.out.println("Invalid choice");

                    break;
            }
        }
    }
    public static void customerLogin(Scanner sc, Customer cs) {
        BankManagement bankManagement=new BankManagement();
        boolean exit = false;
        while (!exit) {
            System.out.println("====CUSTOMER====");
            System.out.println("1. View recent transactions");
            System.out.println("2. Check balance");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit money");
            System.out.println("5. Make a transfer");
            System.out.println("6. Apply for a loan");
            System.out.println("7. Log out");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    //TODO Transactions function
                break;
                case "2":
                    System.out.println(cs.checkBalance());
                    break;
                case "3":
                    //TODO function that allows to withdraw money
                    break;
                case "4":
                    //TODO function to deposit money
                    break;
                case "5":
                    System.out.println("Your IBAN: ");
                    String originIban=sc.nextLine();
                    System.out.println("Destination IBAN: ");
                    String destinationIban=sc.nextLine();
                    System.out.println("Amount of money: ");
                    double amount=sc.nextDouble();
                    System.out.println("Concept: ");
                    String transferConcept=sc.nextLine();
                    bankManagement.doTransfer(originIban,destinationIban,amount,transferConcept);
                    //TODO function to make a transfer
                    break;
                case "6":
                    //TODO function to apply a loan
                    break;
                case "7":
                    exit = true;
                break;
                default:
                    System.out.println("Invalid choice");
                break;
            }
        }
    }
    public static void showBankMenu(Scanner sc)
    {
        boolean exit = false;
        while(!exit)
        {
            System.out.println("====BANK SYSTEM====");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            String choice = sc.nextLine();

            User cs = null;
            switch(choice)
            {
                case "1":
                    cs = Login.Login();
                    if (cs != null)
                    {
                        if(cs instanceof Administrator)
                        {
                            adminLogin(sc, (Administrator) cs);
                        }
                        else if(cs instanceof Customer)
                        {
                            customerLogin(sc,(Customer) cs);
                        }
                        else if(cs instanceof Employee)
                        {
                            employeeLogin(sc, (Employee) cs);
                        }
                    }
                    else
                    {
                        System.out.println("Invalid model.User");
                    }
                    break;
                case "2":
                    cs = Login.SignUpCustumer();
                    if (cs != null)
                    {
                        customerLogin(sc,(Customer) cs);
                    }
                    else
                    {
                        System.out.println("Invalid Username");
                    }
                break;
                case "3":
                    exit = true;
                break;
                default:
                    System.out.println("Invalid choice");
                break;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        showBankMenu(sc);
    }
}
