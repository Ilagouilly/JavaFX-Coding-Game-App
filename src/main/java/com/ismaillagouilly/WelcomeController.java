package com.ismaillagouilly;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.collections.FXCollections;

public class WelcomeController {

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<Integer> questionCountField;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button startQuizButton;

    @FXML
    public void initialize() {
        // Populate ComboBox with difficulty levels
        difficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
        questionCountField.setItems(FXCollections.observableArrayList(5, 10, 20, 30, 40, 50));
    }

    @FXML
    private void startQuiz() {
        // Handle start quiz logic here
        Integer questionCount = questionCountField.getValue();
        String difficulty = difficultyComboBox.getValue();

        // Check if any field is empty
        if ((questionCount != null && questionCount == 0) || difficulty == null) {
            // Show alert for missing fields
            showAlert("All fields are mandatory!");
        } else {
            System.out.println("Questions: " + questionCount);
            System.out.println("Difficulty: " + difficulty);

            // Transition to the next screen (e.g., a quiz screen)
            loadQuizScreen(questionCount, difficulty);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadQuizScreen(Integer questionCount, String difficulty) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quizScreen.fxml"));
            Parent root = loader.load();

            // Pass user data to the quiz controller
            QuizController quizController = loader.getController();
            quizController.initializeQuiz(questionCount, difficulty);

            // Switch to the quiz screen
            Scene scene = new Scene(root);
            Stage stage = (Stage) startQuizButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
