package com.webapp.testportal.repo.examRepo;

import com.webapp.testportal.entity.exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
