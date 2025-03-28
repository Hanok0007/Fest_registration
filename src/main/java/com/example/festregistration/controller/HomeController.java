package com.example.festregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Fest Registration! Use /api/public/hello or /api/secure-endpoint";
    }


}