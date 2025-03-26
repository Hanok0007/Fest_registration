package com.example.festregistration.entity;

import jakarta.persistence.*;  // Fixed import (use jakarta for Spring Boot 3+)
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Fixed typo: GenerateMValue → GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)  // Added length constraint
    private String name;

    @Column(nullable = false)
    private LocalDateTime date;  // Requires java.time import

    @Column(nullable = false, length = 100)  // Added length constraint
    private String location;

    @Column(nullable = false)
    private Integer maxParticipants;  // Changed from primitive int to Integer

    @Lob  // Better alternative for large text than columnDefinition
    @Column(nullable = false)  // Added nullable=false if description is required
    private String description;
}