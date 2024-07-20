package com.rocketseat.planner.activity;

import java.time.LocalDateTime;

public record ActivityRequestPayload(
        String title,
        LocalDateTime occurs_at
) {
}
