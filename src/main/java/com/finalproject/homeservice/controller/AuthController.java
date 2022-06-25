package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.LoginRequest;
import com.finalproject.homeservice.payload.request.ProfessionalUserRequestDto;
import com.finalproject.homeservice.payload.request.RegularUserRequestDto;
import com.finalproject.homeservice.security.JwtTokenProvider;
import com.finalproject.homeservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = tokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    //Register yazılacak
    @PostMapping("/register/regular")
    public ResponseEntity<?> registerRegularUser(@RequestBody RegularUserRequestDto userRequestDto){
        if(userService.checkEmail(userRequestDto.getEmail())){
            return new ResponseEntity<>("Bu email daha önceden alınmış", HttpStatus.BAD_REQUEST);
        }
        userService.registerRegularUser(userRequestDto);
        return new ResponseEntity<>("Kullanıcı kaydı başarıyla tamamlandı",HttpStatus.OK);
    }

    @PostMapping("/register/professional")
    public ResponseEntity<?> registerProfessionalUser(@RequestBody ProfessionalUserRequestDto userRequestDto){
        if(userService.checkEmail(userRequestDto.getEmail())){
            return new ResponseEntity<>("Bu email daha önceden alınmış", HttpStatus.BAD_REQUEST);
        }
        userService.registerProfessionalUser(userRequestDto);
        return new ResponseEntity<>("Kullanıcı kaydı başarıyla tamamlandı",HttpStatus.OK);
    }
}
