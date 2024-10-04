package corner;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Frame() {
        setTitle("Java Trainer Application");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
    }

    private void initializeUI() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel welcomePanel = createWelcomePanel();
        JPanel loginPanel = createLoginPanel();
        JPanel registrationPanel = createRegistrationPanel();

        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registrationPanel, "Registration");

        getContentPane().add(cardPanel);
        cardLayout.show(cardPanel, "Welcome");
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JLabel welcomeLabel = new JLabel("JAVA TRAINER", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial Black", Font.BOLD, 26));
        welcomeLabel.setForeground(new Color(0, 255, 0)); // Green text

        JButton startButton = new JButton("Let's Get Started");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(new Color(0, 200, 100)); // Green background
        startButton.setForeground(Color.WHITE); // White text
        startButton.setOpaque(true);
        startButton.setBorderPainted(false); // No border paint
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK); // Black outer background

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 255)); // Light gray panel (contrasting)

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 15, 10); // Consistent spacing
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK); // Black text (within light gray)
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Username field with modern border and text style
        JTextField usernameField = new JTextField(15);
        usernameField.setForeground(Color.WHITE); // White text
        usernameField.setBackground(new Color(0, 200, 100)); // Green background
        usernameField.setCaretColor(Color.WHITE); // White caret
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Subtle border

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK); // Black text
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); 

        // Password field with modern border and text style
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setForeground(Color.WHITE); // White text
        passwordField.setBackground(new Color(0, 200, 100)); // Green background
        passwordField.setCaretColor(Color.WHITE); // White caret
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Subtle border

        // Login button with subtle shadow effect
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 200, 100)); // Green background
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false); // No border paint
        loginButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Modern border

        // Sign-Up button styled similarly
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setBackground(new Color(0, 200, 100)); // Green background
        signUpButton.setForeground(Color.WHITE); // White text
        signUpButton.setOpaque(true);
        signUpButton.setBorderPainted(false); // No border paint
        signUpButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Modern border
        signUpButton.addActionListener(e -> cardLayout.show(cardPanel, "Registration"));

        // Add components to the panel
        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(loginButton, gbc);
        gbc.gridy++;
        panel.add(signUpButton, gbc);

        mainPanel.add(panel); // Add the login form panel to the center

        return mainPanel;
    }

    private JPanel createRegistrationPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK); // Black outer background

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 255)); // Light gray panel (contrasting)

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Consistent spacing
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK); // Black text
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Username field with modern border
        JTextField usernameField = new JTextField(15);
        usernameField.setForeground(Color.WHITE); // White text
        usernameField.setBackground(new Color(0, 200, 100)); // Green background
        usernameField.setCaretColor(Color.WHITE); // White caret
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Subtle border

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK); // Black text
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Password field with modern border
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setForeground(Color.WHITE); // White text
        passwordField.setBackground(new Color(0, 200, 100)); // Green background
        passwordField.setCaretColor(Color.WHITE); // White caret
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Subtle border

        // Retype Password label
        JLabel retypePasswordLabel = new JLabel("Confirm Password:");
        retypePasswordLabel.setForeground(Color.BLACK); // Black text
        retypePasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Retype Password field with modern border
        JPasswordField retypePasswordField = new JPasswordField(15);
        retypePasswordField.setForeground(Color.WHITE); // White text
        retypePasswordField.setBackground(new Color(0, 200, 100)); // Green background
        retypePasswordField.setCaretColor(Color.WHITE); // White caret
        retypePasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Subtle border

        // Register button styled similarly
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBackground(new Color(0, 200, 100)); // Green background
        registerButton.setForeground(Color.WHITE); // White text
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Modern border

        // Back to Login button styled similarly
        JButton backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(0, 200, 100)); // Green background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Modern border
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        // Add components to the panel
        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(retypePasswordLabel, gbc);
        gbc.gridy++;
        panel.add(retypePasswordField, gbc);
        gbc.gridy++;
        panel.add(registerButton, gbc);
        gbc.gridy++;
        panel.add(backButton, gbc);

        mainPanel.add(panel); // Add the registration form panel to the center

        return mainPanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Frame().setVisible(true));
    }
}
