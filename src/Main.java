import java.util.Scanner;

public class Main {
    public static void employeeLogin(Scanner sc){
        boolean exit=false;
        while(!exit){
            System.out.println("====EMPLOYEE====");
            System.out.println("1. Approve a loan");
            System.out.println("2. Log out");
        }
    }
    public static void adminLogin(Scanner sc) {
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
    public static void customerLogin(Scanner sc) {
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

            switch(choice)
            {
                case "1":
                    String range = "";//TODO Logging function that returns the range or error
                    switch (range)
                    {
                        case "Customer":
                            customerLogin(sc);
                        break;
                        case "Employee":
                            //TODO employeeLogin
                            break;
                        case "Admin":
                            adminLogin(sc);
                        break;
                        default:
                            System.out.println("Invalid range");
                        break;
                    }
                    break;
                case "2":
                    //TODO Register function
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
