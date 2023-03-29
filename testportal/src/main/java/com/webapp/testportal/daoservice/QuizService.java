package com.webapp.testportal.daoservice;

import com.webapp.testportal.entity.exam.Quiz;

import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getAllQuizes();
    public Quiz getQuiz(Long id);
    public void deleteQuiz(Long id);
}
