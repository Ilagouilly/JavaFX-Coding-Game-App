package com.ismaillagouilly.config;

import io.github.cdimascio.dotenv.Dotenv;

public class GeminiApiConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getGeminiApiKey() {
        return dotenv.get("API_KEY");
    }

    public static String getGeminiApiUrl() {
        return "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key="
                + getGeminiApiKey();
    }
}
