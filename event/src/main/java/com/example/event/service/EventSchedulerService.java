package com.example.event.service;

import com.example.event.model.EventMessage;
import com.example.event.model.ExternalApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventSchedulerService {

    private final ExternalApiClient apiClient;
    private final KafkaPublisherService kafkaPublisher;

    private final ScheduledExecutorService executor =
            Executors.newScheduledThreadPool(5);

    public ScheduledFuture<?> schedule(String eventId) {

        return executor.scheduleAtFixedRate(() -> {
            try {
                ExternalApiResponse response =
                        apiClient.fetchEvent(eventId);

                EventMessage message = new EventMessage(
                        response.eventId(),
                        response.currentScore(),
                        Instant.now()
                );

                kafkaPublisher.publish(message);

            } catch (Exception ex) {
                log.error("Error processing event {}", eventId, ex);
            }

        }, 0, 10, TimeUnit.SECONDS);
    }
}