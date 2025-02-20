package io.github.zapolyarnydev.service.impl;

import io.github.zapolyarnydev.entity.User;
import io.github.zapolyarnydev.jwt.JwtUtil;
import io.github.zapolyarnydev.repository.UserRepository;
import io.github.zapolyarnydev.request.LoginRequest;
import io.github.zapolyarnydev.request.RegistrationRequest;
import io.github.zapolyarnydev.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Почта уже используется");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRegistrationTime(LocalDateTime.now());

        userRepository.save(user);

        return JwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Не верные данные!");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}
