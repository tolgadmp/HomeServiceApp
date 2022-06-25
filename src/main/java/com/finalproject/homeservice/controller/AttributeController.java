package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.AttributeRequestDto;
import com.finalproject.homeservice.payload.response.AttributeResponseDto;
import com.finalproject.homeservice.service.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/api/attribute")
@CrossOrigin
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public List<AttributeResponseDto> getAllAttributes(){
        return attributeService.getAllAttributes();
    }

    @PostMapping("/add-attribute/{id}")
    public AttributeResponseDto addAttributeToJob(@RequestBody AttributeRequestDto attributeRequestDto,
                                          @PathVariable(value = "id")long id){
        return attributeService.addAttribute(id, attributeRequestDto);
    }

    @PutMapping("/{id}")
    public AttributeResponseDto updateAttribute(@RequestBody AttributeRequestDto requestDto,
                                                 @PathVariable(name = "id")long id){
        return attributeService.updateAttribute(requestDto,id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable(name = "id")long id){
        attributeService.deleteAttribute(id);

        return new ResponseEntity<>("Özellik başarıyla silindi", HttpStatus.OK);
    }


}
