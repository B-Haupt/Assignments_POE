/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.assignmentpart1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

/**
 *
 * @author britt
 */

public class MessageIT {
 
    @Test
    public void testMessageIdLength() {
        Message msg = new Message(1, "+27718693002", "Hello");
        assertTrue(msg.checkMessageId(), "Message ID should be 10 digits long");
    }

    @Test
    public void testRecipientCorrect() {
        Message msg = new Message(1, "+27718693002", "Hello");
        assertEquals(1, msg.checkRecipientNumber(),
                "Cell phone number successfully captured.");
    }

    @Test
    public void testRecipientIncorrect() {
        Message msg = new Message(1, "08575975889", "Hello");
        assertEquals(0, msg.checkRecipientNumber(),
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    @Test
    public void testMessageHashExact() {
        Message msg = new Message(0, "+27718693002", "Hi there thanks", "0012345678");
        assertEquals("00:0:HITHANKS", msg.createMessageHash());
    }

    @Test
    public void testMessageLengthTooLong() {
        String longContent = "a".repeat(260);
        Message msg = new Message(1, "+27718693002", longContent);
        assertEquals("Message exceeds 250 characters by 10, please reduce size.",
                msg.validateMessageLength());
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message(1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight");
        String hash = msg.createMessageHash();
        assertTrue(hash.matches("\\d{2}:\\d+:[A-Z]+"),
                "Message hash should follow format NN:msgNum:UPPERCASESTRING");
    }

    @Test
    public void testSendMessage() {
        // Skip in CI if headless
        assumeTrue(!GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        Message msg = new Message(1, "+27718693002", "Hello");
        String result = msg.sentMessages("send");
        assertEquals("Message successfully sent.", result);
        assertTrue(Message.returnTotalMessages() > 0,
                "Total messages sent should increase");
    }

    @Test
    public void testDiscardMessage() {
        // This may also trigger JOptionPane, so guard it
        assumeTrue(!GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        Message msg = new Message(2, "08575975889",
                "Hi Keegan, did you receive the payment?");
        String result = msg.sentMessages("disregard");
        assertEquals("Press 0 to delete message", result);
    }

    @Test
    public void testStoreMessage() {
        assumeTrue(!GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        Message msg = new Message(3, "+27718693002", "Hello again");
        String result = msg.sentMessages("store");
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testInvalidChoice() {
        assumeTrue(!GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        Message msg = new Message(4, "+27718693002", "Hello again");
        String result = msg.sentMessages("unknown");
        assertEquals("Invalid choice.", result);
    }

    @Test
    public void testTotalMessagesSentIncrements() {
        assumeTrue(!GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        int before = Message.returnTotalMessages();
        Message msg = new Message(5, "+27718693002", "Test message");
        msg.sentMessages("send");
        int after = Message.returnTotalMessages();
        assertEquals(before + 1, after,
                "Total messages sent should increment by 1 after sending");
    }

}