/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;


import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author britt
 */

public class QuickChatMenu {
    // Setting up the array to store the content
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIds = new ArrayList<>();
    // Access the user
    private User loggedInUser;
    
    // Constructor
    public QuickChatMenu(User user){
        this.loggedInUser = user;
    }
    
    public void start(){
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        boolean running = true;

        while (running){
            String choice = JOptionPane.showInputDialog(
                "Menu:\n" + 
                "Option 1 - Send Messages\n" + 
                "Option 2 - Report\n" + 
                "Option 3 - Stored Messages\n" + 
                "Option 4 - Quit\n" + 
                "Please enter the number for the option: \n");

            if (choice == null) break;
            
            switch (choice){
                case "1": 
                    sendMessages();
                    break;

                case "2":
                    reportsMenu();
                    break;

                case "3":
                    loadStoredMessagesFromJson();
                    break;
                
                case "4":
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages());
                    running = false;
                    break;
                
                default: 
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid number");
            }
        }
    }
    
    
    
    // Function to check if the message format is correct
    private void sendMessages() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number (+27): ");
        if (recipient == null) return;

        Message tempMsg = new Message(1, recipient, "");
        if (tempMsg.checkRecipientNumber() != 1) {
            JOptionPane.showMessageDialog(null, "Invalid phone number.");
            return;
        }

        String messageContent = JOptionPane.showInputDialog("Enter your message:");
        if (messageContent == null) return;
        if (messageContent.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message too long.");
            return;
        }

        Message msg = new Message(1, recipient, messageContent);
        String action = JOptionPane.showInputDialog("Choose action: send / disregard / store");
        if (action == null) return;

        JOptionPane.showMessageDialog(null, msg.sentMessages(action));

        switch (action.toLowerCase()) {
            case "send":
                sentMessages.add(msg);
                break;
            case "disregard":
                disregardedMessages.add(msg);
                break;
            case "store":
                msg.storeMessage(); // only write to file, don't add to storedMessages here
                break;
        }

        messageHashes.add(msg.getMessageHash());
        messageIds.add(msg.getMessageId());
    }


    // Report Menu
    private void reportsMenu(){
        String subChoice = JOptionPane.showInputDialog(
                "Report Menu: (Choose a letter) \n" + 
                "a - Display sender & recipient of all sent messages\n" + 
                "b - Display longest sent message\n" + 
                "c - Search by message ID\n" +
                "d - Search by recipient\n" + 
                "e - Search by hash and delete message\n" + 
                "f - Full report of sent messages\n");
        
        // Ensure something is inputted
        if (subChoice == null) return;
        
        switch (subChoice.toLowerCase()){
            case "a":
                // Showing all the senders and recipient of all sent messages
                if (!sentMessages.isEmpty()){
                    for (Message m: sentMessages)
                    {
                        JOptionPane.showMessageDialog(null, "Sender: "+ loggedInUser.getPhoneNumber() +" | Recipient: " + m.getRecipient());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No sent messages available.");
                }
                break;
                
            case "b":
                // Show the longest sent messages
                if (!sentMessages.isEmpty() || !storedMessages.isEmpty()){
                    Message longest = null;

                    // Combine both lists
                    ArrayList<Message> all = new ArrayList<>();
                    all.addAll(sentMessages);
                    all.addAll(storedMessages);

                    for (Message m : all) {
                        if (longest == null || m.getMessageContent().length() > longest.getMessageContent().length()) {
                            longest = m;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Longest message:\n" + longest.getMessageContent());
                } else {
                    JOptionPane.showMessageDialog(null, "No sent messages available.");
                }
                break;
                
            case "c":
                // Search for a message by ID
                String idSearch = JOptionPane.showInputDialog("Enter Message ID:");
                boolean foundById = false;
                for (int i = 0; i < sentMessages.size();i++){
                    Message m = sentMessages.get(i);
                    if (m.getMessageId().equals(idSearch)){
                        JOptionPane.showMessageDialog(null, "Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessageContent());
                        foundById = true;
                        break; // So that it stops after finding first match
                    }
                }
                
                if (!foundById){
                    JOptionPane.showMessageDialog(null, "Message ID not found");
                }
                break;
                
            case "d":
                // Show all messages sent to a particular recipient
                String recSearch = JOptionPane.showInputDialog("Enter Recipient: ");
                boolean foundByRecipient = false;
                // Search in sent messages
                for (Message m : sentMessages) {
                    if (m.getRecipient().equals(recSearch)) {
                        JOptionPane.showMessageDialog(null, m.printMessage());
                        foundByRecipient = true;
                    }
                }

                // Search in stored messages
                for (Message m : storedMessages) {
                    if (m.getRecipient().equals(recSearch)) {
                        JOptionPane.showMessageDialog(null, m.printMessage());
                        foundByRecipient = true;
                    }
                }
                if (!foundByRecipient){
                    JOptionPane.showMessageDialog(null, "No messages found for that recipients.");
                }
                break;
                
            case "e":
                // Delete a message using the message hash
                String hashSearch = JOptionPane.showInputDialog("Enter Message Hash: ");
                boolean deleted = false;
                // Try deleting from sent
                for (int i = 0; i < sentMessages.size(); i++) {
                    if (sentMessages.get(i).getMessageHash().equals(hashSearch)) {
                        JOptionPane.showMessageDialog(null, "Message \"" + sentMessages.get(i).getMessageContent() + "\" successfully deleted.");
                        sentMessages.remove(i);
                        deleted = true;
                        break;
                    }
                }

                // If not found in sent, try stored
                if (!deleted) {
                    for (int i = 0; i < storedMessages.size(); i++) {
                        if (storedMessages.get(i).getMessageHash().equals(hashSearch)) {
                            JOptionPane.showMessageDialog(null, "Message \"" + storedMessages.get(i).getMessageContent() + "\" successfully deleted.");
                            storedMessages.remove(i);
                            deleted = true;
                            break;
                        }
                    }
                }
                
                if (!deleted){
                    JOptionPane.showMessageDialog(null, "No message found with that hash.");
                }
                break;
                
            case "f":
                // Show the full report of all sent messages
                if (!sentMessages.isEmpty()){
                    StringBuilder report = new StringBuilder("Sent Messages Report:\n\n");
                    for (int i = 0; i < sentMessages.size(); i++){
                        Message m = sentMessages.get(i);
                        report.append(m.printMessage()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, report.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No sent message to display.");
                }
                break;
                
            default:
               JOptionPane.showMessageDialog(null, "Invalid choice.");
               break;
              
             
        }
        
    }
    
     // Load stored messages from JSON file
    private void loadStoredMessagesFromJson() {
        JSONParser parser = new JSONParser();
        storedMessages.clear(); // reset before reloading

        try (BufferedReader br = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject obj = (JSONObject) parser.parse(line);

                String id = (String) obj.get("MessageId");
                String hash = (String) obj.get("MessageHash");
                String recipient = (String) obj.get("Recipient");
                String content = (String) obj.get("Message");
                String flag = (String) obj.get("Flag");

                int number = storedMessages.size() + 1;

                Message msg = new Message(id, number, recipient, content, hash, flag);
                storedMessages.add(msg);
            }

            // Build report for display
            StringBuilder report = new StringBuilder("Stored Messages:\n\n");
            for (Message m : storedMessages) {
                report.append(m.printMessage()).append("\n");
            }
            JOptionPane.showMessageDialog(null, report.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading stored messages: " + e.getMessage());
        }
    }




    // Getters for testing
    public ArrayList<Message> getSentMessages() { return sentMessages; }
    public ArrayList<Message> getStoredMessages() { return storedMessages; }
    public ArrayList<Message> getDisregardedMessages() { return disregardedMessages; }
}
