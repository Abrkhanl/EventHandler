package com.example.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventSchedulerService schedulerService;

    private final Map<String, ScheduledFuture<?>> liveEvents = new ConcurrentHashMap<>();

    public void startEvent(String eventId) {
        if (liveEvents.containsKey(eventId)) return;

        ScheduledFuture<?> future = schedulerService.schedule(eventId);
        liveEvents.put(eventId, future);

        log.info("Event {} marked LIVE", eventId);
    }

    public void stopEvent(String eventId) {
        ScheduledFuture<?> future = liveEvents.remove(eventId);
        if (future != null) {
            future.cancel(true);
            log.info("Event {} marked NOT LIVE", eventId);
        }
    }
}