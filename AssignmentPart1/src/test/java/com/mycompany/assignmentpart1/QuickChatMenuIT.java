/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.assignmentpart1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 *
 * @author britt
 */


public class QuickChatMenuIT {

    private QuickChatMenu menu;
    private Message m1, m2, m3, m4, m5;
    private User testUser;

    @BeforeEach
    public void setUp() {
        // Create a dummy user for testing
        testUser = new User("Test", "User", "+27831234567", "Test@1234", "test_");

        // Pass the user into QuickChatMenu
        menu = new QuickChatMenu(testUser);

        // Test Data Messages
        m1 = new Message(1, "+27834557896", "Did you get the cake?", "Sent", "1111111111");
        m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "2222222222");
        m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "3333333333");
        m4 = new Message(4, "0838884567", "It is dinner time !", "Sent", "4444444444");
        m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.", "Stored", "5555555555");

        // Populate arrays
        menu.getSentMessages().add(m1);
        menu.getSentMessages().add(m4);
        menu.getStoredMessages().add(m2);
        menu.getStoredMessages().add(m5);
        menu.getDisregardedMessages().add(m3);
    }

    // 1. Sent Messages array correctly populated
    @Test
    public void testSentMessagesArrayPopulated() {
        List<Message> sent = menu.getSentMessages();
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessageContent());
        assertEquals("It is dinner time !", sent.get(1).getMessageContent());
    }

    // 2. Display the longest message
    @Test
    public void testDisplayLongestMessage() {
        Message longest = m1;
        for (Message m : List.of(m1, m2, m3, m4)) {
            if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = m;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessageContent());
    }

    // 3. Search for message ID (Message 4)
    @Test
    public void testSearchByMessageId() {
        String searchId = "4444444444"; // forced ID for m4
        Message found = null;
        for (Message m : menu.getSentMessages()) {
            if (m.getMessageId().equals(searchId)) {
                found = m;
                break;
            }
        }
        assertNotNull(found);
        assertEquals("0838884567", found.getRecipient());
        assertEquals("It is dinner time !", found.getMessageContent());
    }

    // 4. Search all messages sent or stored for a recipient
    @Test
    public void testSearchByRecipient() {
        String recipient = "+27838884567";
        StringBuilder results = new StringBuilder();
        for (Message m : menu.getStoredMessages()) {
            if (m.getRecipient().equals(recipient)) {
                results.append(m.getMessageContent()).append(";");
            }
        }
        String resultStr = results.toString();
        assertTrue(resultStr.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(resultStr.contains("Ok, I am leaving without you."));
    }

    // 5. Delete a message using a message hash
    @Test
    public void testDeleteByHash() {
        String hashToDelete = m2.getMessageHash();
        boolean deleted = menu.getStoredMessages().removeIf(m -> m.getMessageHash().equals(hashToDelete));
        assertTrue(deleted, "Message should be deleted");
        for (Message m : menu.getStoredMessages()) {
            assertNotEquals(hashToDelete, m.getMessageHash());
        }
    }

    // 6. Display report of sent messages
    @Test
    public void testDisplayReport() {
        StringBuilder report = new StringBuilder();
        for (Message m : menu.getSentMessages()) {
            report.append(m.printMessage()).append("\n");
        }
        String reportStr = report.toString();
        assertTrue(reportStr.contains("MessageId"));
        assertTrue(reportStr.contains("Hash"));
        assertTrue(reportStr.contains("Recipient"));
        assertTrue(reportStr.contains("Content"));
    }

    // 7. Verify sender identity in report
    @Test
    public void testSenderIdentityInReport() {
        String expectedSender = testUser.getPhoneNumber();
        for (Message m : menu.getSentMessages()) {
            String line = "Sender: " + expectedSender + " | Recipient: " + m.getRecipient();
            assertTrue(line.contains(expectedSender));
        }
    }
}