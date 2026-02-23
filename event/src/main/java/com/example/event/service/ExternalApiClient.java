package com.example.event.service;

import com.example.event.model.ExternalApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class ExternalApiClient {

    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("http://localhost:8081") // mock API
                    .build();

    public ExternalApiResponse fetchEvent(String eventId) {

        return webClient.get()
                .uri("/external/events/{id}", eventId)
                .retrieve()
                .bodyToMono(ExternalApiResponse.class)
                .block();
    }
}