package com.rocketseat.planner.trip;

import java.time.LocalDateTime;
import java.util.UUID;

public record TripData(
        UUID id,
        String destination,
        LocalDateTime starts_at,
        LocalDateTime ends_at,
        Boolean is_confirmed,
        String owner_name,
        String owner_email
) {
    public TripData(Trip trip){
        this(trip.getId(), trip.getDestination(), trip.getStartsAt(), trip.getEndsAt(), trip.getIsConfirmed(), trip.getOwnerName(), trip.getOwnerEmail());
    }
}
