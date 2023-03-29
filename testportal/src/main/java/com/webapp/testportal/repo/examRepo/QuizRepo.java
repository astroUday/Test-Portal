package com.webapp.testportal.repo.examRepo;

import com.webapp.testportal.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Long> {
}
