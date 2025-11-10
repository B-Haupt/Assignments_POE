/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

import java.io.*;
import javax.swing.*;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;

/**
 *
 * @author britt
 */
public class Message {
    
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private static int totalMessageSent = 0;
    private String flag; // Flag to for message [Sent, Stored, Disregard]
    
    // 3 argument constructor for simple use
    public Message(int messageNumber, String recipient, String messageContent){
        this.messageId = generateMessageId();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
    }
    
    // Normal constructor - 4 arguments (flags included)
    public Message(int messageNumber, String recipient, String messageContent, String flag){
        this.messageId = generateMessageId();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
        this.flag = flag;
    }
    
    // Overloading the constructor for fixed id for unit testing
    public Message(int messageNumber, String recipient, String messageContent, String flag, String forcedId){
        this.messageId = forcedId;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
        this.flag = flag;
    }
    
    // Constructor for JSON loading
    public Message(String id, int number, String recipient, String content,  String hash, String flag){
        this.messageId = id;
        this.messageNumber = number;
        this.recipient = recipient;
        this.messageContent = content;
        this.messageHash = hash;
        this.flag = flag;
    }
    
    
    // Random ten digit number for tracking messages
    private String generateMessageId(){
        // Casting and generating 10 random digits
        long id = (long)(Math.random() * 1_000_000_000L);
        // Returning the 10 digit string
        return String.format("%010d",id); 
    }
   
    // Check the length of the message id to make sure it's 10
    public boolean checkMessageId(){
        return messageId.length() == 10;
    }
    
    
    public int checkRecipientNumber(){
        String regex = "^\\+27(?:\\s?\\d){9}$";
        if (Pattern.matches(regex, recipient)){
            return 1; // 1 = Sucess
        }
        return 0; // 0 = Error/Failure
    }
   
    // Creating the Hash for the messages
    public String createMessageHash(){
        // Breaking up String into Array of words and removing special characters/punctuation
        String[] words = messageContent.trim().split("\\s");
        String firstWord = words.length > 0 ? words[0].replaceAll("[^A-Za-z0-9]", "") : ""; //  Accessing first word
        String lastWord = words.length > 0 ? words[words.length - 1].replaceAll("[^A-Za-z0-9]", "") : ""; //Accessing the last word
        return messageId.substring(0,2) + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }
    
    
    public String sentMessages(String choice){
        switch (choice.toLowerCase()){
            case "send":
                totalMessageSent++; // Increasing the message count
                return "Message successfully sent.";
            case "disregard":
                return "Message Disregarded.";
            case "store":
                return "Message successfully stored.";
            default: 
                return "Invalid choice.";   
        }
    }
    
    
    // Ensures message content is 250 characters or fewer
    public String validateMessageLength() {
        if (messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    
    public String printMessage(){
        String sentence = "MessageId: " + messageId + " | Hash: " + messageHash + " | Recipient: " + recipient + " | Content: " + messageContent;
        return sentence;
    }
    
    // Variable is private so have to create a function to access it
    public static int returnTotalMessages(){
        return totalMessageSent;
    }
    
    // Storing the messasge in a json file
    // APA reference: Microsoft. (2025). Copilot (Sept 15 version) [Large language model]. https://copilot.microsoft.com/
    public void storeMessage(){
      JSONObject obj = new JSONObject();
      obj.put("MessageId", messageId);
      obj.put("MessageHash", messageHash);
      obj.put("Recipient", recipient);
      obj.put("Message", messageContent);
      obj.put("Flag", flag);

      try (FileWriter file = new FileWriter("messages.json", true)) {
          file.write(obj.toJSONString() + "\n");
      } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error saving message. Please try again.");
      }
    }
    
    // Setting up Getters
    public String getMessageId() { return  messageId;}
    public String getRecipient() { return recipient;}
    public String getMessageContent() { return messageContent; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag;}
}