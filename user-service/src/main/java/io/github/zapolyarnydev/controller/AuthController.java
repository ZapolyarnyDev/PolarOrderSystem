package io.github.zapolyarnydev.controller;

import io.github.zapolyarnydev.request.LoginRequest;
import io.github.zapolyarnydev.request.RegistrationRequest;
import io.github.zapolyarnydev.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(RegistrationRequest request){
        return ResponseEntity.ok(authService.register(request));
    }


}
