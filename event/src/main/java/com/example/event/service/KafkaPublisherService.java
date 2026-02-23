package com.example.event.service;

import com.example.event.model.EventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaPublisherService {

    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    private static final String TOPIC = "live-events";

    public void publish(EventMessage message) {

        kafkaTemplate.send(TOPIC, message.eventId(), message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish message", ex);
                    } else {
                        log.info("Message published for event {}",
                                message.eventId());
                    }
                });
    }
}