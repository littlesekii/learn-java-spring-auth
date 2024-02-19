package com.littlesekii.auth.modules.auth.service;

import com.littlesekii.auth.modules.user.entity.User;
import com.littlesekii.auth.modules.user.entity.dto.UserAuthenticationDTO;
import com.littlesekii.auth.modules.user.entity.dto.UserRegistrationDTO;
import com.littlesekii.auth.modules.user.repository.UserRepository;
import org.apache.el.parser.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {


    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(UserAuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User)auth.getPrincipal());
        return token;
    }

    public boolean register(UserRegistrationDTO dto) {
        if (userRepository.findByUsername(dto.username()) != null)
            return false;

        User newUser = new User(
                UUID.randomUUID(),
                dto.username(),
                passwordEncoder.encode(dto.password()),
                dto.role()
        );
        userRepository.save(newUser);
        return true;
    }
}
