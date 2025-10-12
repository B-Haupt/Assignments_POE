/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;


import javax.swing.JOptionPane;

/**
 *
 * @author britt
 */

public class QuickChatMenu {
    
    public void start(){
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        boolean running = true;

        while (running){
            String choice = JOptionPane.showInputDialog(
                "Menu:\n" + 
                "Option 1 - Send Messages\n" + 
                "Option 2 - Show recently sent messages\n" + 
                "Option 3 - Quit\n" + 
                "Please enter the number for the option: \n");

            switch (choice){
                case "1": 
                    int totalMessages = 0;
                    boolean validInput = false;
                    
                    // Checking input is valid
                    while (!validInput){
                        try {
                            String input = JOptionPane.showInputDialog("How many messages would you like to send?");
                            if (input == null){
                                JOptionPane.showMessageDialog(null, "Returning to menu.");
                                break;
                            }
                            // Turning String to Int
                            totalMessages = Integer.parseInt(input);
                            
                            // Checking input for message number is valid
                            if (totalMessages > 0){
                                validInput = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Please enter a number greater than 0.");
                            }
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null,"Invalid input. Please enter a valid number" );
                        }
                    }

                    for (int i = 1; i <= totalMessages; i++){
                      
                        String recipient = JOptionPane.showInputDialog("Enter your recipient number (+27): ");
                        // Temp instance to check if recipient input is valid
                        Message tempMsg = new Message(i, recipient, "");

                        if (tempMsg.checkRecipientNumber() == 1){
                            JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            i--;
                            continue;
                        }

                        String messageContent = JOptionPane.showInputDialog("Enter your message:");
                        // Check message length
                        if (messageContent.length() > 250){
                            JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (messageContent.length() - 250) + ", please reduce size.");
                            i--;
                            continue;
                        }

                        JOptionPane.showMessageDialog(null, "Message ready to send.");
                        
                        // Instance of message
                        Message msg = new Message(i, recipient, messageContent);
                        String action = JOptionPane.showInputDialog("Choose action (Type out option - send or disregard or store):\nSend Message \nDisregard Message \nStore Message");
                        JOptionPane.showMessageDialog(null, msg.sentMessages(action));
                    }
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages());
                    running = false;
                    break;

                default: 
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid number");
            }
        }
    }

}
