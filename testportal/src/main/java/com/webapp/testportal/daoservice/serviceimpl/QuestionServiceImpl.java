package com.webapp.testportal.daoservice.serviceimpl;

import com.webapp.testportal.daoservice.QuestionService;
import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;
import com.webapp.testportal.repo.examRepo.QuestionRepo;
import com.webapp.testportal.repo.examRepo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuizRepo quizRepo;


    @Override
    public Question addQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return new HashSet<>(this.questionRepo.findAll());
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long quizId)
    {
        Quiz quiz=quizRepo.findById(quizId).get();
        Set<Question> questionSet= quiz.getQuestionSet();
        List<Question> questionList=new ArrayList<>(questionSet);
        if(questionList.size()> Integer.parseInt(quiz.getNumberOfQuestions())){
            questionList=questionList.subList(0,Integer.parseInt(quiz.getNumberOfQuestions())+1);
        }
        Collections.shuffle(questionList);
        return questionList;
    }

    @Override
    public Question getQuestion(Long id) {
        return this.questionRepo.findById(id).get();
    }

    @Override
    public void deleteQuestion(Long id) {
        this.questionRepo.deleteById(id);
    }

    //find question by id
    public Question get(Long qId){
        return this.questionRepo.findById(qId).get();
    }
}
