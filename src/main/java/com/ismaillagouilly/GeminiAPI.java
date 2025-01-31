package com.ismaillagouilly;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeminiAPI {
    // Load the environment variables from .env file
    private static final Dotenv dotenv = Dotenv.load();  // Static Dotenv instance

    // Use the API_KEY from the loaded environment variables
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + dotenv.get("API_KEY");

    public static List<QuestionData> getQuestionsAndAnswers(String questionCount, String difficultyLevel) {
        List<QuestionData> questionList = new ArrayList<>();

        try {
            // Updated prompt to request a specific response format
            String prompt = String.format(
                "Generate %s Java coding multiple choice question(s) of %s difficulty level. " +
                "For each question, format the response as follows:\n" +
                "Question X:\n" +
                "[Question text]\n" +
                "Answers:\n" +
                "- [Answer 1]\n" +
                "- [Answer 2]\n" +
                "- [Answer 3]\n" +
                "- [Answer 4]\n" +
                "Correct Answer: [Correct answer text]\n" +
                "Explanation: [Explanation text]\n",
                questionCount, difficultyLevel
            );

            // Construct request JSON
            JSONObject requestBody = new JSONObject()
                .put("contents", new JSONArray()
                    .put(new JSONObject()
                        .put("parts", new JSONArray()
                            .put(new JSONObject()
                                .put("text", prompt)))))
                .put("generationConfig", new JSONObject()
                    .put("temperature", 0.7)
                    .put("maxOutputTokens", 800));

            // Create and send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response text
            JSONObject jsonResponse = new JSONObject(response.body());
            if (jsonResponse.has("candidates")) {
                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    String textResponse = candidates.getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text");
                    
                    questionList = parseFormattedQuestions(textResponse);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching questions: " + e.getMessage());
            e.printStackTrace();
        }

        return questionList;
    }

    private static List<QuestionData> parseFormattedQuestions(String textResponse) {
        List<QuestionData> questions = new ArrayList<>();
        
        try {
            // Split the response into individual questions
            String[] questionBlocks = textResponse.split("Question \\d+:");
            
            for (String block : questionBlocks) {
                if (block.trim().isEmpty()) continue;
                
                // Parse individual question block
                QuestionData question = parseQuestionBlock(block.trim());
                if (question != null) {
                    questions.add(question);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing questions: " + e.getMessage());
            e.printStackTrace();
        }
        
        return questions;
    }
    
    private static QuestionData parseQuestionBlock(String block) {
        try {
            // Extract question text
            String questionText = block.substring(0, block.indexOf("Answers:")).trim();
            
            // Extract answers
            Pattern answerPattern = Pattern.compile("- (.+)");
            Matcher answerMatcher = answerPattern.matcher(block);
            List<String> answers = new ArrayList<>();
            while (answerMatcher.find()) {
                answers.add(answerMatcher.group(1).trim());
            }
            
            // Extract correct answer
            Pattern correctPattern = Pattern.compile("Correct Answer: (.+)");
            Matcher correctMatcher = correctPattern.matcher(block);
            String correctAnswer = correctMatcher.find() ? correctMatcher.group(1).trim() : "";
            
            // Extract explanation
            Pattern explanationPattern = Pattern.compile("Explanation: (.+)");
            Matcher explanationMatcher = explanationPattern.matcher(block);
            String explanation = explanationMatcher.find() ? explanationMatcher.group(1).trim() : "";
            
            return new QuestionData(questionText, answers, correctAnswer, explanation);
        } catch (Exception e) {
            System.err.println("Error parsing question block: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}