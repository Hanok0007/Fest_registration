package com.example.festregistration.service;

import com.example.festregistration.dto.UserRegistrationDto;
import com.example.festregistration.entity.User;
import com.example.festregistration.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
@Validated
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        
        // Set role if provided, otherwise keep default PARTICIPANT
        if (registrationDto.getRole() != null) {
            user.setRole(registrationDto.getRole());
        }
        
        return userRepository.save(user);
    }
}