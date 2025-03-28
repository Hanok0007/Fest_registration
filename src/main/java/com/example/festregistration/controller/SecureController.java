package com.example.festregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "This is public!";
    }

    @GetMapping("/secure-endpoint")
    public String secureEndpoint() {
        return "This is secured!";
    }
}