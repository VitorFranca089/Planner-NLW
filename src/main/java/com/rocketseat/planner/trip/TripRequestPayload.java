package com.rocketseat.planner.trip;

import com.rocketseat.planner.validation.EndsAfterStarts;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;
import java.util.List;

@EndsAfterStarts
public record TripRequestPayload(
        String destination,
        @Future
        LocalDateTime starts_at,
        LocalDateTime ends_at,
        List<String> emails_to_invite,
        String owner_name,
        String owner_email
) {
}
