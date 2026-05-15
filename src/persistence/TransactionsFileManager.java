package persistence;

import model.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionsFileManager {

    private static String FILE_PATH = "data/transactions.txt";

    public static void saveTransactions(List<Transaction> transactions) {

        createDataFolder();

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {

            for (Transaction transaction : transactions) {
                writer.println(
                        transaction.getId() + ";" +
                                transaction.getDate() + ";" +
                                transaction.getType() + ";" +
                                transaction.getAmount() + ";" +
                                transaction.getAccountDestination()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static ArrayList<Transaction> loadTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return transactions;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");

                if (parts.length >= 5) {
                    transactions.add(new Transaction(
                            Integer.parseInt(parts[0]),
                            LocalDateTime.parse(parts[1]),
                            parts[2],
                            Double.parseDouble(parts[3]),
                            parts[4]
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }

    private static void createDataFolder() {
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}