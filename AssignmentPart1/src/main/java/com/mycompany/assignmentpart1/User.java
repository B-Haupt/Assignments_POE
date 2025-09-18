/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

/**
 *
 * @author britt
 */

//Creating a User Object to use across the Login and Registration classes
public class User {

        String firstName;
        String secondName;
        String phoneNum;
        String password;
        String username;

        public User(String firstName, String secondName, String phoneNum, String password, String username) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.phoneNum = phoneNum;
            this.password = password;
            this.username = username;
        }
}
