# QuickChat Messaging System (Assignment 3)

## Module Information
- **Module Name:** Programming 1A  
- **Module Code:** PROG5121/p/w  
- **Student Name:** Brittany Marie Haupt  
- **Student Number:** ST10500773
- **Porfolio of Evidence is on the branch Assignment 3**

---

##  Project Overview

This application simulates a basic messaging system called **QuickChat**, designed to demonstrate core programming concepts including string manipulation, array handling, file I/O, and unit testing. Users can send, store, disregard, and report on messages through a simple GUI interface.

---

##  Features Implemented

### 1. Message Handling
- **Send Messages:** Validates recipient number and message length, then stores in `sentMessages` array.
- **Disregard Messages:** Adds to `disregardedMessages` array.
- **Store Messages:** Saves to `storedMessages` array and persists to `messages.json`.

### 2. Array Management
- Arrays used:
  - `sentMessages`
  - `disregardedMessages`
  - `storedMessages`
  - `messageHashes`
  - `messageIds`

### 3. Reporting Tools
- Display sender and recipient of all sent messages.
- Display the longest message across sent and stored.
- Search messages by:
  - Message ID
  - Recipient
  - Message Hash (with delete functionality)
- Full report of all sent messages.

### 4. JSON Integration
- Stored messages are written to and read from `messages.json` using JSON.simple.
- Attribution:  
  > Microsoft. (2025). Copilot (Sept 15 version) [Large language model]. https://copilot.microsoft.com/

### 5. Unit Testing
- JUnit 5 tests implemented in `QuickChatMenuIT.java` and `MessageIT.java`.
- Tests cover:
  - Array population
  - Longest message detection
  - Search by ID and recipient
  - Message deletion by hash
  - Report generation

### 6. Automated Testing
- GitHub Actions workflow (`testJava.yml`) configured to run Maven tests on push/pull request.

---

##  Test Data Used

| Message # | Recipient        | Message Content                                               | Flag      | ID         |
|-----------|------------------|----------------------------------------------------------------|-----------|------------|
| 1         | +27834557896     | Did you get the cake?                                          | Sent      | 1111111111 |
| 2         | +27838884567     | Where are you? You are late! I have asked you to be on time.  | Stored    | 2222222222 |
| 3         | +27834484567     | Yohoooo, I am at your gate.                                    | Disregard | 3333333333 |
| 4         | 0838884567       | It is dinner time !                                            | Sent      | 4444444444 |
| 5         | +27838884567     | Ok, I am leaving without you.                                  | Stored    | 5555555555 |

---

##  How to Run

1. Open the project in NetBeans or your preferred IDE.
2. Run `QuickChatMenu.java` to launch the GUI.
3. Use the menu options to send, store, disregard, and report on messages.
4. Run unit tests using Maven or your IDE’s test runner.



# Assignment Part 2: Sending & Storing Messages  
**Course:** PROG5121 — Programming 1A  



---

## Overview

In **Part 2**, I extend the earlier Registration/Login system to support sending **text messages**, computing a **hash** for each message, and persisting messages to disk in **JSON** format.

---

The application allows users to:
- Create and validate messages
- Check recipient phone numbers
- Generate unique message IDs and hashes
- Validate message length
- Send, disregard, or store messages
- Persist messages to JSON files
- Run automated tests with JUnit 5







---

# Assignment Part 1 - Registration and Login Feature

---

The task for part 1 was to implement a simple user registration and login system. The task must have the following: 
- Username, password and South African cell phone number validation
- Success/failure messages 
- Testing Unit implemented




Regex Attribution
The password and phone-number regex patterns in Login.java were generated with assistance from Microsoft Copilot. They are attributed in code comments following APA guidelines:

Microsoft. (2025). Copilot (Sept 15 version) [Large language model]. https://copilot.microsoft.com/
?
