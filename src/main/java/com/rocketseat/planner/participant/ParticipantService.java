package com.rocketseat.planner.participant;

import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.repository.saveAll(participants);
        // Id do participante sendo impresso no terminal
        // System.out.println(participants.getFirst().getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip){
        Participant newParticipant = new Participant(email, trip);
        this.repository.save(newParticipant);
        return new ParticipantCreateResponse(newParticipant.getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId){
        // Feature a ser implementada
    }

    public void triggerConfirmationEmailToParticipant(String email){
        // Feature a ser implementada
    }

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }
}
