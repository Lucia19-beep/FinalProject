package service;

import model.Administrator;
import model.Customer;
import model.Employee;
import model.User;

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
    public void deleteUser(int id,User user){
        users.remove(id,user);
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
        try(PrintWriter out = new PrintWriter(System.out,true)){
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
                       user = new Customer(line[2],Integer.parseInt(line[1]),line[3],line[4],Integer.parseInt(line[5]),LocalDate.parse(line[6]),Double.parseDouble(line[7]),line[8]);
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
        users.remove(user.getId());
        saveMap(userList);
    }

    public void editUser(UserList userList){
        System.out.println("Enter User Name: ");
        String name=sc.nextLine();
        User user=searchByName(name);
        boolean exit = false;
        while(!exit){
            if(user instanceof Customer){
                Customer customer=(Customer)user;
                System.out.println("Enter New Customer Name: ");
                customer.setName(sc.nextLine());
                System.out.println("Enter New Customer Address: ");
                customer.setAddress(sc.nextLine());
                System.out.println("Enter New Customer Password: ");
                customer.setPassword(hashingPassword(sc.nextLine()));
                System.out.println("Enter New Customer Bank Account: ");

            }
            else if (user instanceof Employee){

            }
            else if (user instanceof Administrator){

            }
            else{
                System.out.println("Invalid User Name");
                exit = true;
            }

        }
    }
}
