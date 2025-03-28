package com.example.festregistration.service;
import com.example.festregistration.entity.Participant;
import com.example.festregistration.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public Participant registerParticipant(Participant participant) {
        return participantRepository.save(participant);
    }
}