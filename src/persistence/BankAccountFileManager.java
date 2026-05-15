package persistence;

import model.BankAccount;
import model.CurrentAccount;
import model.SavingAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountFileManager {

    private static String FILE_PATH = "data/accounts.txt";

    public static void saveAccounts(List<BankAccount> accounts) {

        createDataFolder();

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {

            for (BankAccount account : accounts) {

                if (account instanceof SavingAccount) {
                    writer.println(
                            "SAVING;" +
                                    account.getAccountNumber() + ";" +
                                    account.getBalance() + ";" +
                                    account.getAccountHolder()
                    );
                } else if (account instanceof CurrentAccount) {
                    writer.println(
                            "CURRENT;" +
                                    account.getAccountNumber() + ";" +
                                    account.getBalance() + ";" +
                                    account.getAccountHolder()
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static ArrayList<BankAccount> loadAccounts() {

        ArrayList<BankAccount> accounts = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return accounts;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");

                if (parts.length >= 4) {
                    String type = parts[0];
                    String accountNumber = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    String accountHolder = parts[3];

                    switch (type) {
                        case "SAVING":
                            accounts.add(new SavingAccount(accountNumber, balance, accountHolder, 0));
                            break;

                        case "CURRENT":
                            accounts.add(new CurrentAccount(accountNumber, balance, accountHolder));
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }

        return accounts;
    }

    private static void createDataFolder() {
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}