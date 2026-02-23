package com.example.event.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventStatusRequest(
        @NotBlank String eventId,
        @NotNull Boolean live
) {}