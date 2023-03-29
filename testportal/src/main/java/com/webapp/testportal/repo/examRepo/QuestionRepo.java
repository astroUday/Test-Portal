package com.webapp.testportal.repo.examRepo;

import com.webapp.testportal.entity.exam.Question;
import com.webapp.testportal.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepo extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
