package persistence;

import model.Administrator;
import model.Customer;
import model.Employee;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static String FILE_PATH = "data/users.txt";

    public static void saveUsers(List<User> users) {

        createDataFolder();

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {

            for (User user : users) {

                if (user instanceof Customer customer) {
                    writer.println(
                            "CUSTOMER;" +
                                    customer.getName() + ";" +
                                    customer.getId() + ";" +
                                    customer.getAddress() + ";" +
                                    customer.getRiskLevel() + ";" +
                                    customer.getLinkingDate() + ";" +
                                    customer.getBalance()
                    );
                } else if (user instanceof Employee employee) {
                    writer.println(
                            "EMPLOYEE;" +
                                    employee.getName() + ";" +
                                    employee.getId() + ";" +
                                    employee.getAddress() + ";" +
                                    employee.getPosition() + ";" +
                                    employee.getSalary() + ";" +
                                    employee.isAccessPermits()
                    );
                } else if (user instanceof Administrator administrator) {
                    writer.println(
                            "ADMIN;" +
                                    administrator.getName() + ";" +
                                    administrator.getId() + ";" +
                                    administrator.getAddress() + ";" +
                                    administrator.getAccessLevel()
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static ArrayList<User> loadUsers() {

        ArrayList<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");

                if (parts.length > 0) {
                    switch (parts[0]) {
                        case "CUSTOMER":
                            if (parts.length >= 7) {
                                users.add(new Customer(
                                        parts[1],
                                        Integer.parseInt(parts[2]),
                                        parts[3],
                                        Integer.parseInt(parts[4]),
                                        LocalDate.parse(parts[5]),
                                        Double.parseDouble(parts[6])
                                ));
                            }
                            break;

                        case "EMPLOYEE":
                            if (parts.length >= 7) {
                                users.add(new Employee(
                                        parts[1],
                                        Integer.parseInt(parts[2]),
                                        parts[3],
                                        parts[4],
                                        Double.parseDouble(parts[5]),
                                        Boolean.parseBoolean(parts[6])
                                ));
                            }
                            break;

                        case "ADMIN":
                            if (parts.length >= 5) {
                                users.add(new Administrator(
                                        parts[1],
                                        Integer.parseInt(parts[2]),
                                        parts[3],
                                        Integer.parseInt(parts[4])
                                ));
                            }
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        return users;
    }

    private static void createDataFolder() {
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}