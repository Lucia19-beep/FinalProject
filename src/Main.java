import java.util.Scanner;

public class Main {
    public static void employeeLogin(Scanner sc,User cs){
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
    public static void adminLogin(Scanner sc,User cs) {
        boolean exit = false;
        while (!exit) {
            System.out.print("====ADMIN====");
            System.out.println("1. View recent transactions");
            System.out.println("2. Lock account");
            System.out.println("3. Unlock account");
            System.out.println("4. Log out");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    //TODO Create a function to view recent transactions
                break;
                case "2":
                    //TODO Create a function to lock an account
                    break;
                case "3":
                    //TODO  Unlock Account Function
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
    public static void customerLogin(Scanner sc, User cs) {
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
                    //TODO function to check balanc
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
                            adminLogin(sc,cs);
                        }
                        else if(cs instanceof Customer)
                        {
                            customerLogin(sc,cs);
                        }
                        else if(cs instanceof Employee)
                        {
                            employeeLogin(sc,cs);
                        }
                    }
                    else
                    {
                        System.out.println("Invalid User");
                    }
                    break;
                case "2":
                    cs = Login.SignUpCustumer();
                    if (cs != null)
                    {
                        customerLogin(sc,cs);
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
