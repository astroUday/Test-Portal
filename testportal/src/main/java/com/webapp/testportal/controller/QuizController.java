package com.webapp.testportal.controller;

import com.webapp.testportal.daoservice.QuizService;
import com.webapp.testportal.daoservice.serviceimpl.QuizServiceImpl;
import com.webapp.testportal.entity.exam.Category;
import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizServiceImpl quizService;

    //add quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz);
    }

    //get quiz
    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long id){
        return this.quizService.getQuiz(id);
    }

    //get all quizes
    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok(this.quizService.getAllQuizes());
    }
    //update
    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz){
        return this.quizService.updateQuiz(quiz);
    }
    //delete quiz
    @DeleteMapping("/{quizId}")
    public void deleteQuizById(@PathVariable("quizId")Long id){
        this.quizService.deleteQuiz(id);
    }

    // evaluating incoming response for test
    @PostMapping("/evaluate-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questionList){
        return ResponseEntity.ok(this.quizService.evaluateQuiz(questionList));
    }

}
