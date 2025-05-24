package com.ismaillagouilly.config;

public class GeminiApiConfig {
    public static String getGeminiApiKey() {
        return System.getenv("API_KEY");
    }

    public static String getGeminiApiUrl() {
        return "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key="
                + getGeminiApiKey();
    }
}
