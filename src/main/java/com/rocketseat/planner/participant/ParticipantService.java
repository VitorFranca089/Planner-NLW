package com.rocketseat.planner.participant;

import com.rocketseat.planner.email.EmailConfirmParticipant;
import com.rocketseat.planner.email.EmailService;
import com.rocketseat.planner.trip.Trip;
import com.rocketseat.planner.trip.TripData;
import com.rocketseat.planner.trip.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private EmailService emailService;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.participantRepository.saveAll(participants);
        // Id do participante sendo impresso no terminal
        // System.out.println(participants.getFirst().getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip){
        Participant newParticipant = new Participant(email, trip);
        this.participantRepository.save(newParticipant);
        return new ParticipantCreateResponse(newParticipant.getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId){
        // Feature a ser implementada
        Optional<Trip> trip = this.tripRepository.findById(tripId);
        if(trip.isPresent()){
            List<Participant> participants = this.participantRepository.findByTripId(tripId);
            if(!participants.isEmpty()){
                TripData tripData = new TripData(trip.get());
                participants.forEach(participant ->
                        triggerConfirmationEmailToParticipant(participant.getEmail(), tripData, participant.getId())
                );
            }
        }

    }

    public void triggerConfirmationEmailToParticipant(String email, TripData tripData, UUID participantId){
        // Feature a ser implementada
        EmailConfirmParticipant emailConfirmParticipant = new EmailConfirmParticipant(
                email,
                tripData.destination(),
                tripData.starts_at(),
                tripData.ends_at(),
                tripData.owner_name(),
                participantId
        );
        this.emailService.sendConfirmMail(emailConfirmParticipant);
    }

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return this.participantRepository.findByTripId(tripId).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }

    public ParticipantResponseConfirm confirmParticipant(UUID participantId, ParticipantRequestPayload payload){
        Optional<Participant> participant = this.participantRepository.findById(participantId);
        if(participant.isPresent()){
            Participant rawParticipant = participant.get();
            rawParticipant.setIsConfirmed(true);
            rawParticipant.setName(payload.name());
            this.participantRepository.save(rawParticipant);
            return new ParticipantResponseConfirm(rawParticipant);
        }
        return null;
    }
}
