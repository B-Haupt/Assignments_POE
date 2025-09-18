/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

// Importing the ArrayList and Scanner
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author britt
 */
public class Registration {
    
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<User> users;
    private final Login loginHelper; // use to access methods in Login Class
    
    public Registration(ArrayList<User> users, Login loginHelper){
        this.users = users;
        this.loginHelper = loginHelper;
    }
    
    
    public String registerUser() {
        System.out.println("----REGISTER USER----");
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Enter Surname: ");
        String lastName = scanner.nextLine().trim();
        

        // Loop for username - keep looping till correct data inputted
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (loginHelper.checkUserName(username)) {
                System.out.println(Login.USERNAME_SUCCESS);
                break; // Get out of loop once data input is correct
            } else {
                System.out.println(Login.USERNAME_ERROR);
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            if (loginHelper.checkPasswordComplexity(password)) {
                System.out.println(Login.PASSWORD_SUCCESS);
                break; //Get out of while loop
            } else {
                System.out.println(Login.PASSWORD_ERROR);
            }
        }
        
        String phoneNum;
        while (true) {
            System.out.print("Enter phone number (Example +27 38 293 2891): ");
            phoneNum = scanner.nextLine();
            if (loginHelper.checkCellPhoneNumber(phoneNum)) {
                System.out.println(Login.PHONE_SUCCESS);
                break; //Get out of while loop
            } else {
                System.out.println(Login.PHONE_ERROR);
            }
        }
        
        // Saving the users details
        users.add(new User(firstName,lastName, phoneNum, password, username));
        return "Account successfully registered.";
}
}
