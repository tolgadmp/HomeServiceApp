package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.CategoryDto;
import com.finalproject.homeservice.payload.response.JobDefinitionResponseDto;
import com.finalproject.homeservice.service.CategoryService;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final JobDefinitionService jobDefinitionService;
    private final CategoryService categoryService;

    public HomeController(JobDefinitionService jobDefinitionService,
                          CategoryService categoryService) {

        this.jobDefinitionService = jobDefinitionService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<JobDefinitionResponseDto>> getJobDefinitionsByCategoryId(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(jobDefinitionService.getJobDefinitionByCategory(id),HttpStatus.OK);
    }
}
