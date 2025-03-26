package com.example.festregistration.entity;

import lombok.Data;
import jakarta.persistence.*; // Use jakarta for Spring Boot 3+ or javax for Spring Boot 2.x
import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments") // Fixed annotation name from 'public' to '@Table'
public class Payment { // Fixed square bracket to curly brace
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Fixed typo from '@Nanytone'
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Column(nullable = false, precision = 10) // Added precision for monetary value
    private double amount;

    @Column(nullable = false, length = 20)
    private String status; // PENDING, COMPLETED, FAILED

    @Column(nullable = false)
    private LocalDate paymentDate;
}