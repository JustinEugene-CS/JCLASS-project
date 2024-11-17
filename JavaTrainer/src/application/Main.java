package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import login.CurrentUserSession;
import login.SignOn;
import recommend.Exercise;
import recommend.WorkoutPlan;
import recommend.WorkoutPlanBuilder;
import login.User;

import java.util.ArrayList;;

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
                User user = CurrentUserSession.getCurrentSession().getCurrentUser(); // Fetch user details
                WorkoutPlanBuilder planBuilder = new WorkoutPlanBuilder();
                WorkoutPlan workoutPlan = planBuilder.createWorkoutPlan(user); // Generate workout plan
                showWorkoutPlanScreen(workoutPlan); // Display workout plan
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
                showQuestionnaireScreen(usernameField.getText()); // Show questionnaire after registration
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


    private void showQuestionnaireScreen(String username) {
        VBox questionnaireLayout = new VBox(15);
        questionnaireLayout.setAlignment(Pos.CENTER);
        questionnaireLayout.setPadding(new Insets(20));
        questionnaireLayout.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("Questionnaire");
        titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 32px; -fx-font-weight: bold;");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff;");
        ageField.setMaxWidth(200);

        TextField heightField = new TextField();
        heightField.setPromptText("Height (e.g., 5'9\")");
        heightField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff;");
        heightField.setMaxWidth(200);

        TextField weightField = new TextField();
        weightField.setPromptText("Weight (lbs)");
        weightField.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff;");
        weightField.setMaxWidth(200);

        ComboBox<String> goalsBox = new ComboBox<>();
        goalsBox.getItems().addAll("Strength training", "Weight loss", "Endurance");
        goalsBox.setPromptText("Select your fitness goal");
        goalsBox.setMaxWidth(200);
        goalsBox.setOnAction(event -> {
            System.out.println("GoalsBox Selection: " + goalsBox.getValue());
        });

        ComboBox<Integer> frequencyBox = new ComboBox<>();
        frequencyBox.getItems().addAll(2, 3, 4, 5, 6);
        frequencyBox.setPromptText("Workout frequency per week");
        frequencyBox.setMaxWidth(200);

        ComboBox<String> levelBox = new ComboBox<>();
        levelBox.getItems().addAll("Beginner", "Intermediate", "Advanced");
        levelBox.setPromptText("Fitness level");
        levelBox.setMaxWidth(200);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        submitButton.setOnAction(event -> {
            try {
                int age = Integer.parseInt(ageField.getText().trim());
                int height = parseHeight(heightField.getText().trim());
                int weight = Integer.parseInt(weightField.getText().trim());
                String goals = goalsBox.getValue();
                Integer frequency = frequencyBox.getValue();
                String level = levelBox.getValue();

                System.out.println("Goals: " + goals);
                System.out.println("Frequency: " + frequency);
                System.out.println("Level: " + level);

                if (goals == null || frequency == null || level == null) {
                    showAlert("Selection Error", "Please ensure all selections are made.");
                    return;
                }

                if (StoreUserInfo.store_user_info(age, height, weight, goals, frequency, level)) {
                    showAlert("Questionnaire Submitted", "Thank you, " + username + ", for completing the questionnaire!");
                    User user = CurrentUserSession.getCurrentSession().getCurrentUser(); // Fetch user data
                    WorkoutPlanBuilder planBuilder = new WorkoutPlanBuilder();
                    WorkoutPlan workoutPlan = planBuilder.createWorkoutPlan(user); // Generate workout plan
                    showWorkoutPlanScreen(workoutPlan); // Display workout plan
                } else {
                    showAlert("Database Error", "Failed to store data. Please try again.");
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please ensure all numeric fields are correctly filled.");
            } catch (Exception e) {
                showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            }
        });

        questionnaireLayout.getChildren().addAll(titleLabel, ageField, heightField, weightField, goalsBox, frequencyBox, levelBox, submitButton);

        Scene questionnaireScene = new Scene(questionnaireLayout, 400, 400);
        primaryStage.setScene(questionnaireScene);
        primaryStage.show();
    }



    private void showWorkoutPlanScreen(WorkoutPlan workoutPlan) {
        VBox workoutLayout = new VBox(15);
        workoutLayout.setAlignment(Pos.CENTER);
        workoutLayout.setPadding(new Insets(20));
        workoutLayout.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("Your Workout Plan");
        titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 28px; -fx-font-weight: bold;");

        int currentWeek = workoutPlan.getCur();
        ArrayList<Exercise> exercises = workoutPlan.getWeek(currentWeek);

        VBox exerciseList = new VBox(10);
        for (Exercise exercise : exercises) {
            Label exerciseLabel = new Label(exercise.toString()); // Assuming Exercise class has a meaningful toString
            exerciseLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px;");
            exerciseList.getChildren().add(exerciseLabel);
        }

        Button nextButton = new Button("Next Week");
        nextButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        nextButton.setOnAction(event -> {
            workoutPlan.incCur();
            if (workoutPlan.getCur() < workoutPlan.getDuration()) {
                showWorkoutPlanScreen(workoutPlan); // Show the next week's workout plan
            } else {
                showAlert("End of Plan", "You have completed all weeks of your workout plan!");
            }
        });

        Button backButton = new Button("Logout");
        backButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        backButton.setOnAction(event -> showWelcomeScreen());

        workoutLayout.getChildren().addAll(titleLabel, exerciseList, nextButton, backButton);

        Scene workoutScene = new Scene(workoutLayout, 400, 400);
        primaryStage.setScene(workoutScene);
        primaryStage.show();
    }


    private int parseHeight(String heightStr) {
        String[] parts = heightStr.split("[\'\"]");
        if (parts.length >= 2) {
            int feet = Integer.parseInt(parts[0]);
            int inches = Integer.parseInt(parts[1]);
            return feet * 12 + inches;
        }
        return 0;
    }

    private void showHomePage(String username) {
        VBox homeLayout = new VBox(20);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setPadding(new Insets(20));
        homeLayout.setStyle("-fx-background-color: #2c3e50;");

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 28px; -fx-font-weight: bold;");

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        logoutButton.setOnAction(event -> showWelcomeScreen());

        homeLayout.getChildren().addAll(welcomeLabel, logoutButton);
        Scene homeScene = new Scene(homeLayout, 400, 300);
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}