package com.webapp.testportal.controller;

import com.webapp.testportal.daoservice.CategoryService;
import com.webapp.testportal.daoservice.serviceimpl.CategpryServiceImpl;
import com.webapp.testportal.entity.exam.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategpryServiceImpl categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        this.categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    }

    //getCategory
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long id){
        return this.categoryService.getCategory(id);
    }
    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    //update
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }
    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable("categoryId")Long id){
        this.categoryService.deleteCategory(id);
    }

}
