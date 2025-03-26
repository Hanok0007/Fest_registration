package com.example.festregistration.entity;  // Fixed colon to dot

import lombok.Data;
import jakarta.persistence.*;  // Changed to jakarta (Spring Boot 3+) or keep javax for Spring Boot 2.x

@Entity
@Data
@Table(name = "participants")
public class Participant {  // Fixed square bracket to curly brace
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, length = 100)  // Added length constraint
    private String name;

    @Column(nullable = false, length = 15)  // Added length constraint
    private String contactNumber;

    @Column(nullable = false, length = 100)  // Added length constraint
    private String email;
}