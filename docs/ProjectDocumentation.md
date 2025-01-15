# Project Documentation

## Overview
The Library Management System (LMS) is a desktop application built using Java Swing. It provides a user-friendly interface for managing books and events in a library. The system supports functionalities like adding, searching, and removing books, as well as event registration and viewing registered events.

The system is designed to be simple and intuitive, leveraging Java Swing components like `JTable`, `CardLayout`, and `DefaultTableModel` to manage the user interface and data flow.

## Features and Functionalities

### 1. **Login Panel**
   - **Functionality**: 
     - The login panel authenticates the user based on a username and password.
     - The username must be alphanumeric, and the password must be numeric.
   - **Flow**: 
     - Upon successful login, the user is redirected to the management panel.
     - If login credentials are invalid, an error message is displayed.

### 2. **Management Panel**
   - **Functionality**: 
     - This panel allows users to manage the library's book collection.
     - Users can search books based on title, author, or category.
     - Provides options to add new books and remove selected books.
     - Displays a table with the list of books in the library.
   - **Components**: 
     - Search bar for book search.
     - `JTable` for displaying books.
     - Buttons for adding/removing books and navigating to other sections.

### 3. **Add Book Panel**
   - **Functionality**: 
     - Allows users to add new books to the library.
     - The form includes fields for book title, author, category, ISBN, publication year, and extra details.
     - Upon successful addition, the new book is added to the list of books and displayed in the management panel.

### 4. **Event Registration Panel**
   - **Functionality**: 
     - Users can view and register for various events hosted by the library.
     - Displays a list of upcoming events, including event name, date, time, and description.
     - Users can register for events by entering their name and phone number.
     - Registered events are stored in a table showing the attendee’s details.

### 5. **Registered Events Panel**
   - **Functionality**: 
     - Displays the list of events the user has registered for.
     - Shows details like event name, date, time, description, attendee name, and phone number.

## System Design

### Architecture
The system follows the **Model-View-Controller (MVC)** design pattern:
- **Model**: The data for books and events is managed by the `DefaultTableModel` class.
- **View**: The user interface is created using Java Swing components, including `JTable`, `JTextField`, `JButton`, `JLabel`, and `CardLayout`.
- **Controller**: The logic for managing the user interface (navigation between panels) and handling user input is implemented in the main class `LibraryManagementSystem`.

### Components:
- **Swing Components**: `JTable`, `JPanel`, `JButton`, `JTextField`, `CardLayout`, `DefaultTableModel`
- **Event Handling**: Action listeners and event handlers for user interactions with buttons, tables, and text fields.
  
## Future Enhancements
1. **Database Integration**: Currently, the system relies on in-memory storage. A database like MySQL or SQLite can be integrated to persist data for books, users, and events.
   
2. **Advanced Search**: Implement multi-filter search that allows users to search by multiple parameters simultaneously, such as title and author, category and year, etc.

3. **Authentication System**: Improve the login functionality by adding hashed passwords and user roles (admin, user) for better security and control.

4. **Event Management**: Add features to allow administrators to create, update, and delete events from the event registration panel.

5. **GUI Improvements**: Improve the overall user interface with custom styling and icons, and potentially add features like pagination for long lists of books.

6. **Error Handling and Input Validation**: Add better input validation and error handling to ensure that users cannot submit invalid data (e.g., empty fields, incorrect formats).

## How the System Works

1. **Login Process**:
   - When the system starts, users must log in with a valid username and password.
   - After login, users can access the **Management Panel** to interact with the library.

2. **Book Management**:
   - Users can search for books using the search bar.
   - They can add a new book via the **Add Book Panel**, which requires entering essential details like title, author, and category.
   - Users can also remove selected books from the list.

3. **Event Registration**:
   - Users can register for events by selecting an event and entering their name and phone number.
   - A list of registered events for the user is displayed in the **Registered Events Panel**.

## Running the System

### Requirements:
- **Java Development Kit (JDK)** 8 or later.
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor to compile and run the Java code.

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/hanssdfg/LibraryManagementSystem.git
2. Open the project in your IDE.
3. Navigate to the src/ folder.
4. Open LibraryManagementSystem.java.
5. Compile and run the application.

Once the application starts, you can log in and interact with the system as described in the features section.

### Conclusion
- This system is a simple yet effective library management tool that provides essential functionalities like book management and event registration. By adding more advanced features like database integration and multi-filter search, the system can be improved and scaled for real-world use.