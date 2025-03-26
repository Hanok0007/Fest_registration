package com.example.festregistration.repository;

import com.example.festregistration.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    long countByEventId(Long eventId);
}