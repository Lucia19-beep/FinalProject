package service;

import model.User;
import org.junit.jupiter.api.Test;
import service.UserList;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    void addUserAddsUserToMap() {
        UserList list = new UserList();
        User user = new User("Pepe", 1, "Street 5", "1234");

        list.addUser(1, user);

        assertEquals(1, list.getUsers().size());
        assertEquals("Pepe", list.getUsers().get(1).getName());
    }

    @Test
    void deleteUserDeletesUserFromMap() {
        UserList list = new UserList();
        User user = new User("Pepe", 1, "Street 5", "1234");

        list.addUser(1, user);
        list.deleteUser(1, user);

        assertEquals(0, list.getUsers().size());
    }

    @Test
    void searchByNameReturnsUser() {
        UserList list = new UserList();
        User user = new User("Pepe", 1, "Street 5", "1234");

        list.addUser(1, user);

        User result = list.searchByName("Pepe");

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void searchByNameIgnoresUpperAndLowerCase() {
        UserList list = new UserList();
        User user = new User("Pepe", 1, "Street 5", "1234");

        list.addUser(1, user);

        User result = list.searchByName("pepe");

        assertNotNull(result);
    }

    @Test
    void hashingPasswordChangesEachCharacterByFivePositions() {
        UserList list = new UserList();

        String result = list.hashingPassword("abc");

        assertEquals("fgh", result);
    }
}