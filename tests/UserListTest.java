import model.User;
import org.junit.Test;
import service.UserList;

import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    UserList userList=new UserList();

    @Test
    public void testAddUser(){
        userList.addUser(1,new User("Pepe",2,"Street 5"));
        assertNotNull(userList.getUsers().get(1), "User should exists on map");
        assertEquals("Pepe", userList.getUsers().get(1).getName(), "Name should coincide");
    }
    @Test
    public void testDeleteUser(){
        userList.deleteUser(1,new User("Pepe",2,"Street 5"));
        assertNotNull(userList.getUsers().get(1),"User should be deleted on map");
        assertEquals(0,userList.getUsers().size());
    }
}