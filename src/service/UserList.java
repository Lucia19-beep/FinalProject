package service;

import model.User;

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
}
