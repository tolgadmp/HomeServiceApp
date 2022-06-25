package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Category;
import com.finalproject.homeservice.payload.CategoryDto;
import com.finalproject.homeservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getAllCategories(){
       List<Category> categoryList = this.categoryRepository.findAll();
       List<CategoryDto> categoryDtoList = categoryList.stream()
               .map(category -> CategoryDto.mapEntityToDto(category))
               .collect(Collectors.toList());
       return categoryDtoList;
    }

    public Category getCategoryById(Long id){
        Category category = this.categoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Hata"));

        return category;
    }

    public void createCategory(CategoryDto categoryDto){
        Category category = CategoryDto.mapDtoToEntity(categoryDto);
        categoryRepository.save(category);
    }

    public void updateCategory(long id, CategoryDto categoryDto){
        Category category = categoryRepository.getById(id);
        category.setCategoryName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        categoryRepository.save(category);
    }

    public void deleteCategory(long id){
        Category category = categoryRepository.getById(id);
        categoryRepository.delete(category);
    }

}
