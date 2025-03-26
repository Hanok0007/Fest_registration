package com.example.festregistration.entity;

import lombok.Data;
import jakarta.persistence.*; // Use jakarta for Spring Boot 3+ or javax for Spring Boot 2.x
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {  // Fixed class declaration
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String role; // ADMIN, ORGANIZER, PARTICIPANT

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();
}