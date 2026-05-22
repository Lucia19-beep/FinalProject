package service;

import model.Customer;
import model.User;
import org.junit.jupiter.api.Test;
import service.Login;
import service.UserList;

import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void loginReturnsUserWhenNameAndPasswordAreCorrect() {
        UserList userList = new UserList();
        Login login = new Login();

        String hashedPassword = userList.hashingPassword("1234");
        Customer customer = new Customer("Pepe", 1, "Street 5", hashedPassword, 0, LocalDate.now(), 0.0, "AC1");

        userList.addUser(1, customer);

        Scanner sc = new Scanner("Pepe\n1234\n");

        User result = login.login(userList, sc);

        assertNotNull(result);
        assertEquals("Pepe", result.getName());
    }

    @Test
    void loginReturnsNullWhenPasswordIsWrong() {
        UserList userList = new UserList();
        Login login = new Login();

        String hashedPassword = userList.hashingPassword("1234");
        Customer customer = new Customer("Pepe", 1, "Street 5", hashedPassword, 0, LocalDate.now(), 0.0, "AC1");

        userList.addUser(1, customer);

        Scanner sc = new Scanner("Pepe\nwrong\n");

        User result = login.login(userList, sc);

        assertNull(result);
    }

    @Test
    void loginReturnsNullWhenUserDoesNotExist() {
        UserList userList = new UserList();
        Login login = new Login();

        Scanner sc = new Scanner("NoExiste\n1234\n");

        User result = login.login(userList, sc);

        assertNull(result);
    }
}