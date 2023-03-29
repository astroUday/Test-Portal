package com.webapp.testportal.daoservice.serviceimpl;

import com.webapp.testportal.daoservice.CategoryService;
import com.webapp.testportal.entity.exam.Category;
import com.webapp.testportal.repo.examRepo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategpryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category c) {
        return this.categoryRepo.save(c);
    }

    @Override
    public Category updateCategory(Category c) {
        return this.categoryRepo.save(c);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepo.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return this.categoryRepo.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepo.deleteById(categoryId);
    }
}
