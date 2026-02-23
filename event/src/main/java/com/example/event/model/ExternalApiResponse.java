package com.example.event.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalApiResponse(
        String eventId,
        String currentScore
) {}
