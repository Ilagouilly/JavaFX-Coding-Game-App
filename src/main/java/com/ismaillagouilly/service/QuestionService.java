package com.ismaillagouilly.service;

import com.ismaillagouilly.model.QuestionData;
import java.util.List;

public interface QuestionService {
    List<QuestionData> generateQuestions(Integer questionCount, String difficultyLevel);
}