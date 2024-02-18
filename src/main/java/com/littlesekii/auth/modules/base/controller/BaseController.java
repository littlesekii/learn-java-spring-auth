package com.littlesekii.auth.modules.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BaseController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("Welcome to Auth API. /get");
    }

    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.ok().body("Welcome to Auth API. /post");
    }
}
