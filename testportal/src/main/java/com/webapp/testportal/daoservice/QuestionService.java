package com.webapp.testportal.daoservice;

import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getAllQuestions();
    public List<Question> getQuestionsOfQuiz(Long quizId);
    public Question getQuestion(Long id);
    public void deleteQuestion(Long id);

}
