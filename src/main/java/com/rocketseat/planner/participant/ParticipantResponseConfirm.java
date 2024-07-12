package com.rocketseat.planner.participant;

import com.rocketseat.planner.trip.Trip;

import java.util.UUID;

public record ParticipantResponseConfirm(
        UUID id,
        Boolean is_confirmed,
        String name,
        String email,
        Trip trip
) {
    ParticipantResponseConfirm(Participant participant){
        this(participant.getId(), participant.getIsConfirmed(), participant.getName(), participant.getEmail(), participant.getTrip());
    }
}
