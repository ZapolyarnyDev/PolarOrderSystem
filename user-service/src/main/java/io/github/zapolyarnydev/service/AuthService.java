package io.github.zapolyarnydev.service;

import io.github.zapolyarnydev.request.LoginRequest;
import io.github.zapolyarnydev.request.RegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String register(RegistrationRequest request);

    String login(LoginRequest request);

}
