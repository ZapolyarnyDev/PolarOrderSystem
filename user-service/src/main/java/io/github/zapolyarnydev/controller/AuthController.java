package io.github.zapolyarnydev.controller;

import io.github.zapolyarnydev.request.LoginRequest;
import io.github.zapolyarnydev.request.RegistrationRequest;
import io.github.zapolyarnydev.response.ApiResponse;
import io.github.zapolyarnydev.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(LoginRequest request){
        try {
            String token = authService.login(request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Вход успешный!", token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegistrationRequest request){
        try {
            String token = authService.register(request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Регистрация прошла успешно!", token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<?>> status(@AuthenticationPrincipal UserDetails details){
        if(details != null) return ResponseEntity.ok(new ApiResponse<>(true, "Авторизован", details.getUsername()));
        else return ResponseEntity.ok(new ApiResponse<>(false, "Не авторизован", null));
    }


}
