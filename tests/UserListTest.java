import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    UserList userList=new UserList();
    User user;

    @Test
    public void testAddUser(){
        userList.addUser(1,new User("Pepe",2,"Street 5"));


    }
}