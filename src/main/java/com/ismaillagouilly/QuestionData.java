package com.ismaillagouilly;

import java.util.List;

public class QuestionData {
    private String question;
    private List<String> answers;
    private String correctAnswer;
    private String explanation;

    public QuestionData(String question, List<String> answers, String correctAnswer, String explanation) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }
}
