package service;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserList {
    Map<Integer, User> users;
    Scanner sc=new Scanner(System.in);

    public UserList(){
        users=new HashMap<>();
    }
    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
    public void addUser(int id,User user){
        users.put(id,user);
    }
    public void editUser(int id,User user){
        for(int i=0;i<users.size();i++){
            User actualUser=users.get(i);
            if(actualUser.getId()==id){
                users.put(i,user);
                System.out.println("User changed correctly");
                return;
            }
        }
        System.out.println("User doesn't exist on the map");
    }
    public void deleteUser(int id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                System.out.println("User deleted");
                return;
            }
        }
        System.out.println("User with ID " + id + "doesn't exists.");
    }
    public User searchByName(String name) {
        User user1 = null;
        for (User user2 : users.values()) {
            if (user2.getName().equalsIgnoreCase(name)) {
                user1 = user2;
            }
        }

        return user1;
    }
    public void saveMap(UserList userList){
        try(PrintWriter out = new PrintWriter(new FileWriter("users.txt",false))) {
            for(User user: userList.getUsers().values()){
                if(user instanceof Customer){
                    Customer customer=(Customer)user;
                    out.println("Customer,"+customer.getId()+","+customer.getName()+","+customer.getAddress()+","+customer.getPassword()+","+customer.getLinkingDate()+","+customer.getRiskLevel()+","+customer.getBalance()+","+customer.getAccountNumber()+","+customer.getBankAccount());
                }
                else if (user instanceof Employee)
                {
                    Employee employee=(Employee)user;
                    out.println("Employee,"+employee.getId()+","+employee.getName()+","+employee.getAddress()+","+employee.getPassword()+","+employee.getPosition()+","+employee.getSalary()+","+employee.isAccessPermits());
                }
                else if (user instanceof Administrator)
                {
                    Administrator admin=(Administrator)user;
                    out.println("Administrator,"+admin.getId()+","+admin.getName()+","+admin.getAddress()+","+admin.getPassword()+","+admin.getAccessLevel());
                }
                else {
                    out.println("Unknown User "+user.getId());
                }
            }
        }
        catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public void saveMapUser(int id,User user){
        try(PrintWriter pw = new PrintWriter(new FileWriter("users.txt",true))){
            if(user instanceof Customer){
                Customer customer=(Customer)user;
                pw.println("Customer,"+id+","+customer.getName()+","+customer.getAddress()+","+customer.getPassword()+","+customer.getLinkingDate()+","+customer.getRiskLevel()+","+customer.getBalance()+","+customer.getAccountNumber()+","+customer.getBankAccount());
            }
            else if (user instanceof Employee)
            {
                Employee employee=(Employee)user;
                pw.println("Employee,"+id+","+employee.getName()+","+employee.getAddress()+","+employee.getPassword()+","+employee.getPosition()+","+employee.getSalary()+","+employee.isAccessPermits());
            }
            else if (user instanceof Administrator)
            {
                Administrator admin=(Administrator)user;
                pw.println("Administrator,"+id+","+admin.getName()+","+admin.getAddress()+","+admin.getPassword()+","+admin.getAccessLevel());
            }
            else {
                pw.println("Unknown User");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadMapUser(){
        User user=null;
        try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
            String lineString;
            while((lineString = br.readLine())!=null){
                String[] line=lineString.split(",");
                String type=line[0];
                int id=Integer.parseInt(line[1]);
                switch(type){
                    case "Customer":
                        user = new Customer(
                                line[2],
                                Integer.parseInt(line[1]),
                                line[3],
                                line[4],
                                Integer.parseInt(line[6]),
                                LocalDate.parse(line[5]),
                                Double.parseDouble(line[7]),
                                line[8]
                        );
                        break;
                    case "Employee":
                        user = new Employee(line[2],Integer.parseInt(line[1]),line[3],line[4],line[5],Double.parseDouble(line[6]),Boolean.parseBoolean(line[7]));
                        break;
                    case "Administrator":
                        user = new Administrator(line[2],Integer.parseInt(line[1]),line[3],line[4],Integer.parseInt(line[5]));
                        break;
                }
                users.put(user.getId(),user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String hashingPassword(String password) {
        StringBuilder newPassword = new StringBuilder();
        char[] passwordParts = password.toCharArray();

        for (char c : passwordParts) {
            c = (char) (c + 5);
            newPassword.append(c);
        }
        return newPassword.toString();
    }

    public void deleteUser(UserList userList){
        System.out.println("Enter User Name: ");
        String name=sc.nextLine();
        User user=searchByName(name);
        if(user != null){
            users.remove(user.getId());
            saveMap(userList);
            System.out.println("User "+name+" has been deleted!");
        }
        else {
            System.out.println("User "+name+" not found!");
        }
    }

    public void editUser(UserList userList){
        System.out.println("Enter User Name: ");
        String name=sc.nextLine();
        User user=searchByName(name);
        if (user!=null){
            boolean exit = false;
            while(!exit) {
                System.out.println("=== EDIT USER ===");
                System.out.println("1. Edit Name");
                System.out.println("2. Edit Address");
                System.out.println("3. Edit Password");
                System.out.println("4. Exit");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Enter New Name: ");
                        user.setName(sc.nextLine());
                        break;
                    case "2":
                        System.out.println("Enter New Address: ");
                        user.setAddress(sc.nextLine());
                        break;
                    case "3":
                        System.out.println("Enter New Password: ");
                        user.setPassword(hashingPassword(sc.nextLine()));
                        break;
                    case "4":
                        exit = true;
                        break;
                    default:
                        System.out.println("Wrong Choice");
                        break;
                }
                saveMap(userList);
                System.out.println("Sucefully Edited!");
            }
        }
        else{
            System.out.println("User not found");
        }
    }
}