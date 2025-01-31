package com.ismaillagouilly.controller;

import java.util.List;
import java.util.Map;

import com.ismaillagouilly.model.QuestionData;
import com.ismaillagouilly.service.GeminiAIQuestionGenerator;
import com.ismaillagouilly.service.QuestionService;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class QuizController {

    @FXML
    private Label questionLabel;
    @FXML
    private Button answer1Button;
    @FXML
    private Button answer2Button;
    @FXML
    private Button answer3Button;
    @FXML
    private Button answer4Button;
    @FXML
    private Button validateButton;
    @FXML
    private Label explanationLabel;

    private List<QuestionData> questions; // List of questions
    private int currentQuestionIndex = 0; // Track current question
    private final Map<Button, String> answerMap = new HashMap<>();
    private QuestionService questionService;

    public QuizController() {
        this.questionService = new GeminiAIQuestionGenerator();
    }

    public void initializeQuiz(Integer questionCount, String difficultyLevel) {
        questions = questionService.generateQuestions(questionCount, difficultyLevel);

        if (questions == null || questions.isEmpty()) {
            explanationLabel.setText("⚠️ Error loading questions. Please try again.");
            return;
        }

        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int index) {
        if (index >= questions.size()) {
            explanationLabel.setText("🎉 Quiz Completed!");

            // Make the label clickable to return to the Welcome Page
            validateButton.setText("Return to Welcome Page");
            validateButton.setOnAction(event -> returnToWelcomePage());
            return;
        }

        QuestionData questionData = questions.get(index);
        questionLabel.setText(questionData.getQuestion());

        List<String> choices = questionData.getAnswers();
        Button[] buttons = { answer1Button, answer2Button, answer3Button, answer4Button };
        answerMap.clear();

        for (int i = 0; i < buttons.length; i++) {
            final int ix = i;
            buttons[ix].setText(choices.get(ix));
            answerMap.put(buttons[ix], choices.get(ix));
            buttons[ix].setStyle("-fx-background-color: #e1e1e1;");
            buttons[ix].setOnAction(event -> selectAnswer(buttons[ix]));
        }

        validateButton.setOnAction(event -> validateAnswer(questionData));
    }

    private void returnToWelcomePage() {
        try {
            String path = "/com/ismaillagouilly/views/welcomeScreen.fxml";
            URL resource = getClass().getResource(path);

            if (resource == null) {
                throw new IOException("FXML file not found: " + path);
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            // Get current stage and switch to welcome screen
            Stage stage = (Stage) explanationLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void selectAnswer(Button selectedButton) {
        answerMap.keySet().forEach(btn -> btn.setStyle("-fx-background-color: #e1e1e1;"));
        selectedButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    }

    private void validateAnswer(QuestionData questionData) {
        String selectedAnswer = answerMap.entrySet().stream()
                .filter(entry -> entry.getKey().getStyle().contains("#4CAF50"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("");

        if (selectedAnswer.equals(questionData.getCorrectAnswer())) {
            explanationLabel.setText("✅ Correct! 🎉\n" + questionData.getExplanation());
        } else {
            explanationLabel.setText("❌ Incorrect. The correct answer is: \n" + questionData.getCorrectAnswer() + ": "
                    + questionData.getExplanation());
        }

        // Move to next question after a short delay
        validateButton.setDisable(true);
        new Thread(() -> {
            try {
                Thread.sleep(2000); // 2-second delay before next question
                javafx.application.Platform.runLater(() -> {
                    validateButton.setDisable(false);
                    explanationLabel.setText("");
                    loadQuestion(++currentQuestionIndex);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
