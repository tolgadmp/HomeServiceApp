package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Category;
import com.finalproject.homeservice.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
       return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        Category category = this.categoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Hata"));

        return category;
    }

    public void createCategory(Category category){
        this.categoryRepository.save(category);
    }


}
