package com.webapp.testportal.daoservice.serviceimpl;


import com.webapp.testportal.daoservice.QuizService;
import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;
import com.webapp.testportal.repo.examRepo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionServiceImpl questionService;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz) ;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Set<Quiz> getAllQuizes() {
        return new HashSet<>(this.quizRepo.findAll());
    }

    @Override
    public Quiz getQuiz(Long id) {
        return this.quizRepo.findById(id).get();
    }

    @Override
    public void deleteQuiz(Long id) {
        this.quizRepo.deleteById(id);
    }

    // Evaluation of Quiz response
    public Map<Object,Object> evaluateQuiz(List<Question> questionList) {

        AtomicInteger attempted= new AtomicInteger(0);
        AtomicInteger correctAnswers= new AtomicInteger(0);
        AtomicInteger marksGot = new AtomicInteger(0);

        questionList.forEach(q -> {
            Question question = this.questionService.get(q.getQuesId());
            if(q.getGivenAnswers()!=null && question.getAnswer().trim().equals(q.getGivenAnswers().trim())){
                correctAnswers.getAndIncrement();
                attempted.getAndIncrement();
                marksGot.getAndAdd(Integer.parseInt(question.getQuiz().getMaxMarks()) / Integer.parseInt(question.getQuiz().getNumberOfQuestions()));
            }
            else if(q.getAnswer()!=null || !q.getAnswer().trim().equals("") ){
                attempted.getAndIncrement();
            }
            else{
                marksGot.getAndAdd(-Integer.parseInt(question.getQuiz().getMaxMarks()) / Integer.parseInt(question.getQuiz().getNumberOfQuestions()));
            }
        });

        Map<Object,Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"totalAttempted",attempted);
        return map;
    }
}
