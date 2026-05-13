package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    Map<Integer, User> users;

    public UserList(){
        users=new HashMap<>();
    }
}
