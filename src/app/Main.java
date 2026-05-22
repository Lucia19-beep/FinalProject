package app;

import model.*;
import persistence.BankAccountFileManager;
import service.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static BankManagement bankManagement = new BankManagement();

    public static void employeeLogin(Scanner sc, User cs) {
        boolean exit = false;
        while (!exit) {
            System.out.println("====EMPLOYEE====");
            System.out.println("1. Approve a loan");
            System.out.println("2. Log out");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Loan approved function is not implemented yet.");
                    break;
                case "2":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void adminLogin(Scanner sc, User cs) {
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
                    showAllTransactions();
                    break;
                case "2":
                    changeAccountLock(sc, true);
                    break;
                case "3":
                    changeAccountLock(sc, false);
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
        Customer customer = (Customer) cs;
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
                    viewMyTransactions(customer);
                    break;
                case "2":
                    checkBalance(customer);
                    break;
                case "3":
                    withdraw(sc, customer);
                    break;
                case "4":
                    deposit(sc, customer);
                    break;
                case "5":
                    transfer(sc, customer);
                    break;
                case "6":
                    System.out.println("Loan request function is not implemented yet.");
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

    public static void showBankMenu(Scanner sc, UserList userList) {
        boolean exit = false;
        while (!exit) {
            System.out.println("====BANK SYSTEM====");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            String choice = sc.nextLine();

            Login login = new Login();

            switch (choice) {
                case "1":
                    User cs = login.login(userList, sc);

                    if (cs instanceof Administrator) {
                        adminLogin(sc, cs);
                    } else if (cs instanceof Customer) {
                        customerLogin(sc, cs);
                    } else if (cs instanceof Employee) {
                        employeeLogin(sc, cs);
                    } else {
                        System.out.println("Invalid User");
                    }
                    break;

                case "2":
                    registerCustomer(sc, userList);
                    break;

                case "3":
                    saveAccounts();
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void loadAccounts() {
        bankManagement.getAccountsList().setAccounts(BankAccountFileManager.loadAccounts());
    }

    private static void saveAccounts() {
        BankAccountFileManager.saveAccounts(bankManagement.getAccountsList().getAccounts());
    }

    private static BankAccount getCustomerAccount(Customer customer) {
        BankAccount account = null;

        if (customer.getAccountNumber() != null && !customer.getAccountNumber().isEmpty()) {
            account = bankManagement.getAccountsList().searchByAccountNumber(customer.getAccountNumber());
        }

        return account;
    }

    private static void viewMyTransactions(Customer customer) {
        BankAccount account = getCustomerAccount(customer);

        if (account != null) {
            bankManagement.getTransactionsList().showAccountHistory(account.getAccountNumber());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance(Customer customer) {
        BankAccount account = getCustomerAccount(customer);

        if (account != null) {
            System.out.println("Your balance is " + account.getBalance() + "€");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw(Scanner sc, Customer customer) {
        BankAccount account = getCustomerAccount(customer);

        if (account != null) {
            System.out.print("Amount of money: ");
            double amount = readDouble(sc);

            bankManagement.withdrawMoney(account.getAccountNumber(), amount, LocalDate.now().toString());
            customer.setBalance(account.getBalance());
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit(Scanner sc, Customer customer) {
        BankAccount account = getCustomerAccount(customer);

        if (account != null) {
            System.out.print("Amount of money: ");
            double amount = readDouble(sc);

            bankManagement.depositMoney(account.getAccountNumber(), amount, LocalDate.now().toString());
            customer.setBalance(account.getBalance());
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transfer(Scanner sc, Customer customer) {
        BankAccount account = getCustomerAccount(customer);

        if (account != null) {
            System.out.print("Destination IBAN: ");
            String destinationIban = sc.nextLine();

            System.out.print("Amount of money: ");
            double amount = readDouble(sc);

            System.out.print("Concept: ");
            String transferConcept = sc.nextLine();

            bankManagement.doTransfer(account.getAccountNumber(), destinationIban, amount, transferConcept);
            customer.setBalance(account.getBalance());
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void showAllTransactions() {
        if (bankManagement.getTransactionsList().getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String accountNumber : bankManagement.getTransactionsList().getTransactions().keySet()) {
                System.out.println("Account: " + accountNumber);
                bankManagement.getTransactionsList().showAccountHistory(accountNumber);
            }
        }
    }

    private static void changeAccountLock(Scanner sc, boolean locked) {
        System.out.print("Account number: ");
        String accountNumber = sc.nextLine();

        BankAccount account = bankManagement.getAccountsList().searchByAccountNumber(accountNumber);

        if (account != null) {
            account.setLocked(locked);
            saveAccounts();

            if (locked) {
                System.out.println("Account locked.");
            } else {
                System.out.println("Account unlocked.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void registerCustomer(Scanner sc, UserList userList) {
        Login login = new Login();

        int nextId = userList.getUsers().size() + 1;

        login.registrerCustomer(userList, sc);

        String accountNumber = "AC" + nextId;
        BankAccount account = new CurrentAccount(accountNumber, 0.0, String.valueOf(nextId));

        if (bankManagement.getAccountsList().searchByAccountNumber(accountNumber) == null) {
            bankManagement.getAccountsList().addBankAccount(account);
            saveAccounts();
        }
    }

    private static double readDouble(Scanner sc) {
        double number = 0;
        boolean valid = false;

        while (!valid) {
            try {
                number = Double.parseDouble(sc.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }

        return number;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserList userList = new UserList();

        File usersFile = new File("users.txt");

        if (usersFile.exists()) {
            userList.loadMapUser();
        }

        bankManagement.setUsersList(userList);
        loadAccounts();

        showBankMenu(sc, userList);
    }
}