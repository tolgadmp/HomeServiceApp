package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.ChoiceRequestDto;
import com.finalproject.homeservice.payload.response.ChoiceResponseDto;
import com.finalproject.homeservice.service.ChoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/api/choice")
@CrossOrigin
public class ChoiceController {

    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ChoiceResponseDto> createChoice(@PathVariable(name = "id")long id,
                                                         @RequestBody ChoiceRequestDto requestDtoq){
        return new ResponseEntity<>(choiceService.createChoice(id,requestDtoq), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChoiceResponseDto> updateChoice(@PathVariable(name = "id")long id,
                                                          @RequestBody ChoiceRequestDto requestDto){
        return new ResponseEntity<>(choiceService.updateChoice(id,requestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChoice(@PathVariable(name = "id")long id){
        choiceService.deleteChoice(id);
        return new ResponseEntity<>("Seçenek başarıyla silindi",HttpStatus.OK);
    }
}
