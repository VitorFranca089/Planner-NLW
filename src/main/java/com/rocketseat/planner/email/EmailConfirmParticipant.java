package com.rocketseat.planner.email;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmailConfirmParticipant(
        String email,
        String destination,
        LocalDateTime starts_at,
        LocalDateTime ends_at,
        String owner_name,
        UUID participantId
) {
}
