package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.AddressRequestDto;
import com.finalproject.homeservice.payload.response.UserResponseDto;
import com.finalproject.homeservice.service.AddressService;
import com.finalproject.homeservice.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    public UserController(UserService userService,
                          @Lazy AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> viewProfile(){
        String email = getUserByContext();
        return new ResponseEntity<>(userService.viewProfile(email), HttpStatus.OK);
    }

    @PostMapping("/add-address")
    public ResponseEntity<String> createAddress(@RequestBody AddressRequestDto requestDto){
        String email = getUserByContext();
        addressService.createAddress(requestDto,email);
        return new ResponseEntity<>("Adres ekleme başarılı", HttpStatus.OK);
    }

    @PutMapping("/update-adress/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable(name = "id")long id,
                                                @RequestBody AddressRequestDto requestDto){
        addressService.updateAddress(id, requestDto);
        return new ResponseEntity<>("Adres başarıyla güncellendi", HttpStatus.OK);
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable(name = "id")long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Adres başarıyla silindi", HttpStatus.OK);
    }

    private String getUserByContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
