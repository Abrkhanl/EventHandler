package com.example.event.model;

import java.time.Instant;

public record EventMessage(
        String eventId,
        String currentScore,
        Instant timestamp
) {}