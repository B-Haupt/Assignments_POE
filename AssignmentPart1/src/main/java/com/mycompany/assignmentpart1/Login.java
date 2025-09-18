/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

// Importing ArrayList
import java.util.ArrayList;
// Importing Scanner
import java.util.Scanner;

// Need Pattern to match password and phoneNum via regex
import java.util.regex.Pattern;

/**
 *
 * @author britt
 */
public class Login {

    private ArrayList<User> users;
    public String messageForUser = "";

    // The messages for the users - Error or Success    
    public static final String USERNAME_SUCCESS = "Username successfully captured.";
    public static final String USERNAME_ERROR = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";

    public static final String PASSWORD_SUCCESS = "Password successfully captured.";
    public static final String PASSWORD_ERROR = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

    public static final String PHONE_SUCCESS = "Cell number successfully captured.";
    public static final String PHONE_ERROR = "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";

    public static final String LOGIN_ERROR = "Username or password incorrect, please try again";

    public Login(ArrayList<User> users) {
        this.users = users;
    }

    public boolean checkUserName(String username) {
        if (username.contains("_")) {
            if (username.length() <= 5) {
                return true;
            }
        }
        return false;
    }

    // Password regex generated with assistance from Microsoft Copilot (Sept 15 version) [Large language model].
    // APA reference: Microsoft. (2025). Copilot (Sept 15 version) [Large language model]. https://copilot.microsoft.com/
    public boolean checkPasswordComplexity(String password) {
        String regexPass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
        return Pattern.matches(regexPass, password);
    }

    // Cell phone regex generated with assistance from Microsoft Copilot (Sept 15 version) [Large language model].
    // APA reference: Microsoft. (2025). Copilot (Sept 15 version) [Large language model]. https://copilot.microsoft.com/
    public boolean checkCellPhoneNumber(String phoneNum) {
        String regex = "^\\+27(?:\\s?\\d){9}$";
        return Pattern.matches(regex, phoneNum);
    }

    public String registerUser() {
        Registration register = new Registration(users, this);
        return register.registerUser();
    }

    public boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----USER LOGIN----");

        System.out.print("Enter your username: ");
        String usernameInput = scanner.nextLine();

        System.out.print("Enter your password: ");
        String passwordInput = scanner.nextLine();

        for (User currentUser : users) {
            if (currentUser.username.equals(usernameInput) && currentUser.password.equals(passwordInput)) {
                messageForUser = "Welcome " + currentUser.firstName + " ," + currentUser.secondName + " it is great to see you again.";
                return true; // stop the method immediately as login is successful;y
            }
        }

        messageForUser = LOGIN_ERROR;
        return false; // incorrect details for login
    }

    public String returnLoginStatus(boolean status) {
        return messageForUser;
    }

    // Validation methods for the JUnit testing
    public String validateUsername(String username) {
        return checkUserName(username) ? USERNAME_SUCCESS : USERNAME_ERROR;
    }

    public String validatePassword(String password) {
        return checkPasswordComplexity(password) ? PASSWORD_SUCCESS : PASSWORD_ERROR;
    }

    public String validateCellPhone(String phoneNum) {
        return checkCellPhoneNumber(phoneNum) ? PHONE_SUCCESS : PHONE_ERROR;
    }

    // Stimulate Login in 
    public boolean attemptLogin(String usernameInput, String passwordInput) {
        for (User currentUser : users) {
            if (currentUser.username.equals(usernameInput)
                    && currentUser.password.equals(passwordInput)) {
                messageForUser = "Welcome " + currentUser.firstName + ", " + currentUser.secondName + " it is great to see you.";
                return true;
            }
        }
        messageForUser = LOGIN_ERROR;
        return false;
    }

}
