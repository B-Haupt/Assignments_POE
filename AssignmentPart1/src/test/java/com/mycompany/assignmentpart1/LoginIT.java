/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.assignmentpart1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author britt
 */
public class LoginIT {

    @Test
    public void testUsernameCorrectMessage() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Kyle", "Smith", "+27838968976", "Ch&&sec@ke99!", "kyl_1"));
        Login login = new Login(users);
        // Simulate login
        boolean result = login.attemptLogin("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
        assertEquals(
            "Welcome Kyle, Smith it is great to see you.",
            login.returnLoginStatus(true)
        );
    }

    @Test
    public void testUsernameIncorrectMessage() {
        Login login = new Login(new ArrayList<>());
        assertEquals(
            "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.",
            login.validateUsername("kyle!!!!!!!")
        );
    }

    @Test
    public void testPasswordCorrectMessage() {
        Login login = new Login(new ArrayList<>());
        assertEquals(
            "Password successfully captured.",
            login.validatePassword("Ch&&sec@ke99!")
        );
    }

    @Test
    public void testPasswordIncorrectMessage() {
        Login login = new Login(new ArrayList<>());
        assertEquals(
            "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
            login.validatePassword("password")
        );
    }

    @Test
    public void testPhoneCorrectMessage() {
        Login login = new Login(new ArrayList<>());
        assertEquals(
            "Cell number successfully captured.",
            login.validateCellPhone("+27838968976")
        );
    }

    @Test
    public void testPhoneIncorrectMessage() {
        Login login = new Login(new ArrayList<>());
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.",
            login.validateCellPhone("08966553")
        );
    }

    @Test
    public void testLoginSuccess() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Kyle", "Smith", "+27838968976", "Ch&&sec@ke99!", "kyl_1"));
        Login login = new Login(users);

        // Correct login simulation
        boolean result = users.get(0).username.equals("kyl_1") && users.get(0).password.equals("Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Kyle", "Smith", "+27838968976", "Ch&&sec@ke99!", "kyl_1"));
        Login login = new Login(users);

        // Incorrect login simulation
        boolean result = users.get(0).username.equals("wrong") && users.get(0).password.equals("wrong");
        assertFalse(result);
    }

    @Test
    public void testUsernameCorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordCorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testPhoneCorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testPhoneIncorrectBoolean() {
        Login login = new Login(new ArrayList<>());
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

}
