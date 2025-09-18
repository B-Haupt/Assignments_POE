/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.assignmentpart1;
// Importing ArrayList

import java.util.ArrayList;
// Importing Scanner
import java.util.Scanner;

/**
 *
 * @author britt
 */
public class AssignmentPart1 {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        Login loginSystem = new Login(users);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        // Let the user to choose to login or register or exit
        while (running) {
            System.out.println();
            System.out.println("Type 'Register' to create an account");
            System.out.println("Type 'Login' to log into your account");
            System.out.println("Type 'Exit' to exit program");

            System.out.print("Enter: ");
            // Trim and turn input to lowercase
            String userChoice = scanner.nextLine().trim().toLowerCase();

            // using a switch statement to check user choice
            switch (userChoice) {
                case "register":
                    System.out.println(loginSystem.registerUser());
                    break;

                case "login":
                    boolean loggedIn = loginSystem.loginUser();
                    System.out.println(loginSystem.returnLoginStatus(loggedIn));
                    break;
                    
                case "exit":
                    System.out.println("Exiting program");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Choose a different option");
            }
        }
        scanner.close();
    }
}
