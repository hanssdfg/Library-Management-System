import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class LibraryManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DefaultTableModel bookModel, eventModel, registeredEventModel;
    private JTable bookTable, eventTable, registeredEventTable;
    private JTextField searchTextField, nameTextField, phoneTextField;
    private JComboBox<String> searchTypeComboBox;

    public LibraryManagementSystem() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initializeLoginPanel();
        initializeManagementPanel();
        initializeAddBookPanel();
        initializeEventPanel();
        initializeRegisteredEventsPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void setButtonTheme(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private void setLabelTheme(JLabel label) {
        label.setForeground(Color.WHITE);
    }

    private void setTextFieldTheme(JTextField textField) {
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
    }

    private void setTextAreaTheme(JTextArea textArea) {
        textArea.setBackground(Color.GRAY);
        textArea.setForeground(Color.WHITE);
    }

    private void initializeLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(5, 2));
        loginPanel.setBackground(Color.BLACK);

        JLabel userLabel = new JLabel("Username:");
        setLabelTheme(userLabel);
        JTextField userText = new JTextField();
        setTextFieldTheme(userText);

        JLabel passwordLabel = new JLabel("Password:");
        setLabelTheme(passwordLabel);
        JPasswordField passwordText = new JPasswordField();
        setTextFieldTheme(passwordText);

        JButton loginButton = new JButton("Login");
        setButtonTheme(loginButton);
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (isValidLogin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                cardLayout.show(mainPanel, "Management");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(new JLabel()); // Empty cell for alignment
        loginPanel.add(loginButton);

        mainPanel.add(loginPanel, "Login");
    }

    private boolean isValidLogin(String username, String password) {
        return username.matches("\\w+") && password.matches("\\d+");  // Valid username and password check
    }

    private void initializeManagementPanel() {
        JPanel managementPanel = new JPanel(new BorderLayout());
        managementPanel.setBackground(Color.BLACK);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.BLACK);

        JLabel searchLabel = new JLabel("Search by:");
        setLabelTheme(searchLabel);
        searchPanel.add(searchLabel);

        searchTypeComboBox = new JComboBox<>(new String[]{"Title", "Author", "Category"});
        searchPanel.add(searchTypeComboBox);

        searchTextField = new JTextField(15);
        setTextFieldTheme(searchTextField);
        searchPanel.add(searchTextField);

        JButton searchButton = new JButton("Search");
        setButtonTheme(searchButton);
        searchButton.addActionListener(e -> searchBooks());
        searchPanel.add(searchButton);

        bookModel = new DefaultTableModel(new String[]{"Title", "Author", "Category", "ISBN", "Year", "Extra Details"}, 0);
        bookTable = new JTable(bookModel);
        bookTable.setBackground(Color.DARK_GRAY);
        bookTable.setForeground(Color.WHITE);
        JScrollPane bookScrollPane = new JScrollPane(bookTable);

        JButton addBookButton = new JButton("Add Book");
        setButtonTheme(addBookButton);
        addBookButton.addActionListener(e -> cardLayout.show(mainPanel, "Add Book"));

        JButton eventRegistrationButton = new JButton("Register Event");
        setButtonTheme(eventRegistrationButton);
        eventRegistrationButton.addActionListener(e -> cardLayout.show(mainPanel, "Event Registration"));

        JButton viewRegisteredEventsButton = new JButton("View Registered Events");
        setButtonTheme(viewRegisteredEventsButton);
        viewRegisteredEventsButton.addActionListener(e -> cardLayout.show(mainPanel, "Registered Events"));

        JButton removeBookButton = new JButton("Remove Selected Book");
        setButtonTheme(removeBookButton);
        removeBookButton.addActionListener(e -> removeSelectedBook());

        JButton logoutButton = new JButton("Logout");
        setButtonTheme(logoutButton);
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(addBookButton);
        buttonPanel.add(eventRegistrationButton);
        buttonPanel.add(viewRegisteredEventsButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(logoutButton);

        managementPanel.add(searchPanel, BorderLayout.NORTH);
        managementPanel.add(bookScrollPane, BorderLayout.CENTER);
        managementPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(managementPanel, "Management");
    }

    private void searchBooks() {
        String query = searchTextField.getText().toLowerCase();
        String searchType = searchTypeComboBox.getSelectedItem().toString().toLowerCase();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(bookModel);
        bookTable.setRowSorter(sorter);

        if (query.trim().length() == 0) {
            sorter.setRowFilter(null);  // Show all rows
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, searchType.equals("title") ? 0 : searchType.equals("author") ? 1 : 2));
            if (bookTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frame, "No results found for the search query.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void initializeAddBookPanel() {
        JPanel addBookPanel = new JPanel(new GridLayout(7, 2));
        addBookPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Title:");
        setLabelTheme(titleLabel);
        JTextField titleText = new JTextField();
        setTextFieldTheme(titleText);

        JLabel authorLabel = new JLabel("Author:");
        setLabelTheme(authorLabel);
        JTextField authorText = new JTextField();
        setTextFieldTheme(authorText);

        JLabel categoryLabel = new JLabel("Category:");
        setLabelTheme(categoryLabel);
        JTextField categoryText = new JTextField();
        setTextFieldTheme(categoryText);

        JLabel isbnLabel = new JLabel("ISBN:");
        setLabelTheme(isbnLabel);
        JTextField isbnText = new JTextField();
        setTextFieldTheme(isbnText);

        JLabel yearLabel = new JLabel("Year:");
        setLabelTheme(yearLabel);
        JTextField yearText = new JTextField();
        setTextFieldTheme(yearText);

        JLabel extraDetailsLabel = new JLabel("Extra Details:");
        setLabelTheme(extraDetailsLabel);
        JTextArea extraDetailsText = new JTextArea(3, 20);
        setTextAreaTheme(extraDetailsText);
        JScrollPane extraDetailsScrollPane = new JScrollPane(extraDetailsText);

        JButton addButton = new JButton("Add Book");
        setButtonTheme(addButton);
        addButton.addActionListener(e -> {
            String title = titleText.getText();
            String author = authorText.getText();
            String category = categoryText.getText();
            String isbn = isbnText.getText();
            String year = yearText.getText();
            String extraDetails = extraDetailsText.getText();

            if (!title.isEmpty() && !author.isEmpty() && !category.isEmpty() && !isbn.isEmpty() && !year.isEmpty()) {
                bookModel.addRow(new Object[]{title, author, category, isbn, year, extraDetails});
                JOptionPane.showMessageDialog(frame, "Book Added Successfully!");
                titleText.setText("");
                authorText.setText("");
                categoryText.setText("");
                isbnText.setText("");
                yearText.setText("");
                extraDetailsText.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter title, author, category, ISBN, and year.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = new JButton("Go Back");
        setButtonTheme(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        addBookPanel.add(titleLabel);
        addBookPanel.add(titleText);
        addBookPanel.add(authorLabel);
        addBookPanel.add(authorText);
        addBookPanel.add(categoryLabel);
        addBookPanel.add(categoryText);
        addBookPanel.add(isbnLabel);
        addBookPanel.add(isbnText);
        addBookPanel.add(yearLabel);
        addBookPanel.add(yearText);
        addBookPanel.add(extraDetailsLabel);
        addBookPanel.add(extraDetailsScrollPane);
        addBookPanel.add(backButton);
        addBookPanel.add(addButton);

        mainPanel.add(addBookPanel, "Add Book");
    }

    private void removeSelectedBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            bookModel.removeRow(bookTable.convertRowIndexToModel(selectedRow));
            JOptionPane.showMessageDialog(frame, "Selected Book Removed Successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "No Book Selected.");
        }
    }

    private void initializeEventPanel() {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.setBackground(Color.BLACK);

        // Input fields for name and phone number
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name:");
        setLabelTheme(nameLabel);
        nameTextField = new JTextField(15);
        setTextFieldTheme(nameTextField);

        JLabel phoneLabel = new JLabel("Phone:");
        setLabelTheme(phoneLabel);
        phoneTextField = new JTextField(15);
        setTextFieldTheme(phoneTextField);

        JButton registerEventButton = new JButton("Register for Selected Event");
        setButtonTheme(registerEventButton);
        registerEventButton.addActionListener(e -> registerSelectedEvent());

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneTextField);
        inputPanel.add(registerEventButton);

        // Event Table
        eventModel = new DefaultTableModel(new String[]{"Event Name", "Date", "Time", "Description"}, 0);
        eventTable = new JTable(eventModel);
        eventTable.setBackground(Color.DARK_GRAY);
        eventTable.setForeground(Color.WHITE);
        JScrollPane eventScrollPane = new JScrollPane(eventTable);

        // Go Back Button
        JButton goBackButton = new JButton("Go Back");
        setButtonTheme(goBackButton);
        goBackButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(goBackButton);

        eventPanel.add(inputPanel, BorderLayout.NORTH);
        eventPanel.add(eventScrollPane, BorderLayout.CENTER);
        eventPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add some sample events
        eventModel.addRow(new Object[]{"Author Talk", "2024-11-15", "19:00", "Talk by a renowned author"});
        eventModel.addRow(new Object[]{"Novel Reading", "2024-12-05", "17:30", "Novel reading and discussion"});
        eventModel.addRow(new Object[]{"Book Fair", "2024-11-25", "09:00", "Various authors showcase their books"});
        eventModel.addRow(new Object[]{"Novel Reading", "2024-12-01", "16:00", "A live novel reading by the author"});

        mainPanel.add(eventPanel, "Event Registration");
    }

    private void registerSelectedEvent() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        int selectedRow = eventTable.getSelectedRow();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in both name and phone fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedRow != -1) {
            String eventName = eventModel.getValueAt(selectedRow, 0).toString();
            String eventDate = eventModel.getValueAt(selectedRow, 1).toString();
            String eventTime = eventModel.getValueAt(selectedRow, 2).toString();
            String eventDescription = eventModel.getValueAt(selectedRow, 3).toString();

            registeredEventModel.addRow(new Object[]{eventName, eventDate, eventTime, eventDescription, name, phone});
            JOptionPane.showMessageDialog(frame, "Registered for the selected event.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            nameTextField.setText("");
            phoneTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "No Event Selected.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeRegisteredEventsPanel() {
        JPanel registeredEventsPanel = new JPanel(new BorderLayout());
        registeredEventsPanel.setBackground(Color.BLACK);

        registeredEventModel = new DefaultTableModel(new String[]{"Event Name", "Date", "Time", "Description", "Name", "Phone"}, 0);
        registeredEventTable = new JTable(registeredEventModel);
        registeredEventTable.setBackground(Color.DARK_GRAY);
        registeredEventTable.setForeground(Color.WHITE);
        JScrollPane registeredEventScrollPane = new JScrollPane(registeredEventTable);

        JButton backButton = new JButton("Go Back");
        setButtonTheme(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(backButton);

        registeredEventsPanel.add(registeredEventScrollPane, BorderLayout.CENTER);
        registeredEventsPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(registeredEventsPanel, "Registered Events");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}