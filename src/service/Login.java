package service;

import model.Administrator;
import model.Customer;
import model.Employee;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public User login(UserList usertList, Scanner sc){
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Your Password: ");
        String password = sc.nextLine();

<<<<<<< Updated upstream
        User user = usertList.searchByName(name);
        if (user != null)
=======
        String[] data = SignUserData();
        String name = data[0];
        String address = data[1];
        String password = data[2];

        int id = GetNumberID();
        int riskLevel = 0;
        LocalDate linkingDate = LocalDate.now();
        double balance = 0.0;
        String line = "model.Customer," +name+','+id+','+address+','+riskLevel+','+linkingDate+','+balance+','+password;
        SignUser(line);
        cs = new Customer(name, id, address, riskLevel, linkingDate, balance);
        return cs;
    }
    public static String[] SignAdminData()
    {
        String[] data = SignUserData();
        System.out.print("Acces Level: ");
        int accessLevel = 0;
        String[] dataAdmin = {data[0],data[1],data[2],Integer.toString(accessLevel)};
        return dataAdmin;
    }
    public static User SignUpAdmin()
    {
        Administrator admin = null;
        String[] data = SignAdminData();
        String name = data[0];
        String address = data[1];
        String password = data[2];
        int id = GetNumberID();
        String accessLevelString = data[3];
        int accessLevel = Integer.parseInt(accessLevelString);
        String line = "Admin,"+name+','+id+','+address+','+accessLevel+','+password;
        SignUser(line);
        admin = new Administrator(name, id, address, accessLevel);
        return admin;
    }
    public static String[] SignEmployeeData()
    {
        String[] data = SignUserData();
        System.out.print("Position: ");
        String position = sc.nextLine();
        System.out.print("Salary: ");
        String salary = sc.nextLine();
        System.out.print("Permits: Yes/No ");
        String permission = sc.nextLine();
        String[] dataEmployee ={data[0],data[1],data[2],position,salary,permission};
        return dataEmployee;
    }
    public static User SignUpEmployee()
    {
        Employee employee = null;
        String[] data = SignEmployeeData();
        String name = data[0];
        String address = data[1];
        String password = data[2];
        String position = data[3];
        String salary = data[4];
        String permission = data[5];
        int id = GetNumberID();

        Double salaryDouble = Double.parseDouble(salary);

        boolean accessPermit = false;
        if (permission.equalsIgnoreCase("Yes"))
>>>>>>> Stashed changes
        {
            String hashedPassword = usertList.hashingPassword(password);
            if(!user.getPassword().equals(hashedPassword)){
                System.out.println("Wrong Password");
                user = null;
            }
        }
        else {
            System.out.println("Invalid Username");
        }
        return user;
    }

    public void registrerCustomer(UserList usertList, Scanner sc)
    {
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Your Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Your Password: ");
        String password = sc.nextLine();

        String hashed = usertList.hashingPassword(password);
        int id = usertList.getUsers().size()+1; // Check in case of error, get the number of users and add 1

        Customer customer = new Customer(name,id,address,hashed,0,LocalDate.now(),0.0,"AC"+id); //REVISAR
        usertList.addUser(id,customer);
        usertList.saveMapUser(id,customer);
        System.out.println("Customer Registered");
    }

    public void registrerEmployee(UserList usertList, Scanner sc){
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        String hashed = usertList.hashingPassword(password);

        int id = usertList.getUsers().size()+1;

        System.out.print("Enter the position: ");
        String position = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        System.out.print("Access Permit (true/false): ");
        boolean accessPermit = Boolean.parseBoolean(sc.nextLine());

        Employee employee = new Employee(name,id,address,hashed,position,salary,accessPermit);

        usertList.addUser(id,employee);
        usertList.saveMapUser(id,employee);
        System.out.println("Employee Registered");
    }

    public void registrerAdmin(UserList usertList, Scanner sc)
    {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        String hashed = usertList.hashingPassword(password);

        int id = usertList.getUsers().size()+1;

        System.out.print("Enter acces level");
        int accessLevel = Integer.parseInt(sc.nextLine());

        Administrator administrator = new Administrator(name,id,address,hashed,accessLevel);
        usertList.addUser(id,administrator);
        usertList.saveMapUser(id,administrator);
        System.out.println("Admin Registered");
    }
}
