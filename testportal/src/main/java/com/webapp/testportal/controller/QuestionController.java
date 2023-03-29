package com.webapp.testportal.controller;

import com.webapp.testportal.daoservice.QuestionService;
import com.webapp.testportal.daoservice.serviceimpl.QuestionServiceImpl;
import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        this.questionService.addQuestion(question);
        return ResponseEntity.ok(question);
    }

    //get question
    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable("questionId") Long id){
        return this.questionService.getQuestion(id);
    }

    //get all question of a quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getAllQuestionsFromQuiz(@PathVariable("quizId") Long quizId){
        return ResponseEntity.ok(this.questionService.getQuestionsOfQuiz(quizId));
    }
    //update
    @PutMapping("/")
    public Question updateQuestions(@RequestBody Question question){
        return this.questionService.updateQuestion(question);
    }
    //delete question
    @DeleteMapping("/{questionId}")
    public void deleteQuizById(@PathVariable("questionId")Long id){
        this.questionService.deleteQuestion(id);
    }




}
