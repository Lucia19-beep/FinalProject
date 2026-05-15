package service;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @org.junit.jupiter.api.Test
    void hashingPassword() {
        //Test ID : Login1
        //Nombre: HashPassword
        //Preconditions: Password
        //Steps: Generate password hash
        //Date: junio
        //Expected result: ozsnt
        assertEquals("ozsnt",Login.HashingPassword("junio"));
    }

    @org.junit.jupiter.api.Test
    void getNumberID() {
        //Test ID : Login2
        //Nombre: Generate 1
        //Preconditions: X
        //Steps: Generate ID
        //Date:
        //Expected result: 2
        assertEquals(2,Login.GetNumberID());
    }

    @org.junit.jupiter.api.Test
    void signUser() {
    }

    @org.junit.jupiter.api.Test
    void signData() {
    }

    @org.junit.jupiter.api.Test
    void signUpCustumer() {
    }

    @org.junit.jupiter.api.Test
    void signUpAdmin() {
    }

    @org.junit.jupiter.api.Test
    void signUpEmployee() {
    }

    @org.junit.jupiter.api.Test
    void login() {
    }

    @org.junit.jupiter.api.Test
    void loginAdmin() {
    }

    @org.junit.jupiter.api.Test
    void loginCustomer() {
    }

    @org.junit.jupiter.api.Test
    void loginEmployee() {
    }
}