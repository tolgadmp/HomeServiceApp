package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.CategoryDto;
import com.finalproject.homeservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/api/category")
public class CategoryContoller {

    private final CategoryService categoryService;

    public CategoryContoller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto){
        categoryService.createCategory(categoryDto);
        return new ResponseEntity<>("Kategori başarıyla oluşturuldu",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id")long id,
                                                 @RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(id,categoryDto);
        return new ResponseEntity<>("Kategori başarıyla güncellendi",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id")long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Kategori başarıyla silindi",HttpStatus.OK);
    }
}
