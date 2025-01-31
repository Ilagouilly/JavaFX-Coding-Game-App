package com.ismaillagouilly.controller;

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
import java.net.URL;

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

        // Optional: Set default values
        difficultyComboBox.setValue("Medium");
        questionCountField.setValue(5);
    }

    @FXML
    private void startQuiz() {
        Integer questionCount = questionCountField.getValue();
        String difficulty = difficultyComboBox.getValue();

        if (questionCount == null || difficulty == null) {
            showAlert("Please select question count and difficulty!");
            return;
        }
        loadQuizScreen(questionCount, difficulty);
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
            String path = "/com/ismaillagouilly/views/quizScreen.fxml";
            URL resource = getClass().getResource(path);

            if (resource == null) {
                throw new IOException("FXML file not found: " + path);
            }

            FXMLLoader loader = new FXMLLoader(resource);
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
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
