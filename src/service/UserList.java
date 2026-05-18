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

public class UserList {
    Map<Integer, User> users;

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
}
