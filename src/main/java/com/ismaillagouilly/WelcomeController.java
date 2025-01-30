package com.ismaillagouilly;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;

public class WelcomeController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField questionCountField;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button startQuizButton;

    @FXML
    public void initialize() {
        // Populate ComboBox with difficulty levels
        difficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
    }

    @FXML
    private void startQuiz() {
        // Handle start quiz logic here
        String name = nameField.getText();
        String email = emailField.getText();
        String difficulty = difficultyComboBox.getValue();
        String questionCount = questionCountField.getText();

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Questions: " + questionCount);
        System.out.println("Difficulty: " + difficulty);
    }
}
