package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.OfferRequestDto;
import com.finalproject.homeservice.payload.response.OfferResponseDto;
import com.finalproject.homeservice.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PreAuthorize("hasRole('ProfessionalUser')")
    @GetMapping
    public ResponseEntity<List<OfferResponseDto>> getOffersByUser(){
        String email = getUserByContext();
        return new ResponseEntity<>(offerService.getOfferByUser(email),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ProfessionalUser')")
    @PostMapping("/{jobId}")
    public ResponseEntity<String> createOffer(@PathVariable(name = "jobId")long id,
                                              @RequestBody OfferRequestDto requestDto){
        String email = getUserByContext();
        offerService.createOffer(id,requestDto,email);
        return new ResponseEntity<>("Teklif başarıyla oluşturuldu", HttpStatus.OK);
    }
    @PostMapping("/accept/{jobId}")
    public ResponseEntity<String> acceptOffer(@PathVariable(name = "jobId")long id,
                                              @RequestParam(required = false,name = "accept") boolean accept){
        offerService.acceptOffer(id, accept);
        return new ResponseEntity<>("Teklif başarıyla kabul edildi", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ProfessionalUser')")
    @PutMapping("{/offerId}")
    public ResponseEntity<String> updateOffer(@PathVariable(name = "offerId")long id,
                                              @RequestBody OfferRequestDto offerRequestDto){
        offerService.updateOffer(id, offerRequestDto);
        return new ResponseEntity<>("Güncelleme işlemi başarılı", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ProfessionalUser')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable(name = "id")long id){
        offerService.deleteOffer(id);
        return new ResponseEntity<>("Silme işlemi başarılı", HttpStatus.OK);
    }

    private String getUserByContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
