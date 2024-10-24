package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Workout App");

        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(20));
        welcomeLayout.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("JAVA TRAINER");
        titleLabel.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 36px; -fx-font-weight: bold;");

        Button startButton = new Button("Let's Get Started");
        startButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        startButton.setOnAction(event -> showLoginOrRegisterScreen());

        welcomeLayout.getChildren().addAll(titleLabel, startButton);
        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    private void showLoginOrRegisterScreen() {
        VBox choiceLayout = new VBox(20);
        choiceLayout.setAlignment(Pos.CENTER);
        choiceLayout.setPadding(new Insets(20));
        choiceLayout.setStyle("-fx-background-color: #2c3e50;");

        Label choiceLabel = new Label("Choose an Option");
        choiceLabel.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 24px; -fx-font-weight: bold;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        loginButton.setOnAction(event -> showLoginScreen());

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        registerButton.setOnAction(event -> showRegisterScreen());

        choiceLayout.getChildren().addAll(choiceLabel, loginButton, registerButton);
        Scene choiceScene = new Scene(choiceLayout, 400, 300);
        primaryStage.setScene(choiceScene);
        primaryStage.show();
    }

    private void showLoginScreen() {
        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        loginButton.setOnAction(event -> {
            if (SignOn.existing_user_login(usernameField.getText(), passwordField.getText())) {
                showAlert("Login Successful", "Welcome back, " + usernameField.getText() + "!");
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white;");
        backButton.setOnAction(event -> showLoginOrRegisterScreen());

        loginLayout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, backButton);
        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showRegisterScreen() {
        VBox registerLayout = new VBox(10);
        registerLayout.setAlignment(Pos.CENTER);
        registerLayout.setPadding(new Insets(20));
        registerLayout.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1;");

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        registerButton.setOnAction(event -> {
            if (SignOn.add_user(usernameField.getText(), passwordField.getText())) {
                showAlert("Registration Successful", "Welcome to JAVA TRAINER, " + usernameField.getText() + "!");
            } else {
                showAlert("Registration Failed", "Username may already be taken or there's a database issue.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white;");
        backButton.setOnAction(event -> showLoginOrRegisterScreen());

        registerLayout.getChildren().addAll(titleLabel, usernameField, passwordField, registerButton, backButton);
        Scene registerScene = new Scene(registerLayout, 400, 300);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
