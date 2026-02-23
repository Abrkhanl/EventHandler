package com.example.event.controller;

import com.example.event.model.EventStatusRequest;
import com.example.event.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventStatusController {

    private final EventService eventService;

    @PostMapping("/status")
    public ResponseEntity<Void> updateStatus(
            @Valid @RequestBody EventStatusRequest request) {

        if (request.live()) {
            eventService.startEvent(request.eventId());
        } else {
            eventService.stopEvent(request.eventId());
        }

        log.info("Updated event {} status to {}", request.eventId(), request.live());
        return ResponseEntity.ok().build();
    }
}