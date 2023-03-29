package com.webapp.testportal.daoservice;

import com.webapp.testportal.entity.exam.Category;

import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category c);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public void deleteCategory(Long categoryId);
}
