package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.ExpertiseDto;
import com.finalproject.homeservice.service.ExpertiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/api/expertise")
public class ExpertiseController {

    private final ExpertiseService expertiseService;

    public ExpertiseController(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpertiseDto>> getAllExpertises(){
        return new ResponseEntity<>(expertiseService.getAllExpertises(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpertiseDto> createExpertise(@RequestBody ExpertiseDto expertiseDto){
        return new ResponseEntity<>(expertiseService.craeateExpertise(expertiseDto),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpertiseDto> updateExpertise(@PathVariable(name = "id")long id,
                                                        @RequestBody ExpertiseDto expertiseDto){
        return new ResponseEntity<>(expertiseService.updateExpertise(id,expertiseDto),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpertise(@PathVariable(name = "id")long id){
        expertiseService.deleteExpertise(id);
        return new ResponseEntity<>("Uzmanlık başarıyla silindi",HttpStatus.OK);
    }
}
