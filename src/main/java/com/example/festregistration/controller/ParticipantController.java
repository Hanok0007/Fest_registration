package com.example.festregistration.controller;

import com.example.festregistration.entity.Participant;
import com.example.festregistration.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ResponseEntity<Participant> registerParticipant(@RequestBody Participant participant) {
        Participant registeredParticipant = participantService.registerParticipant(participant);
        return ResponseEntity.ok(registeredParticipant);
    }
}