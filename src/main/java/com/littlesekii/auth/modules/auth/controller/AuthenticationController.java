package com.littlesekii.auth.modules.auth.controller;

import com.littlesekii.auth.modules.auth.service.AuthenticationService;
import com.littlesekii.auth.modules.user.entity.User;
import com.littlesekii.auth.modules.user.entity.dto.UserAuthenticationDTO;
import com.littlesekii.auth.modules.user.entity.dto.UserRegistrationDTO;
import com.littlesekii.auth.modules.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserAuthenticationDTO req) {
        String token = service.authenticate(req);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegistrationDTO req) {
        if (!service.register(req))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
