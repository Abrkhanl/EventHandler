package com.example.event;

import com.example.event.service.EventSchedulerService;
import com.example.event.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ScheduledFuture;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    EventSchedulerService schedulerService;

    @InjectMocks
    EventService eventService;

    @Test
    void shouldStartEvent() {
        when(schedulerService.schedule("123"))
                .thenReturn(mock(ScheduledFuture.class));

        eventService.startEvent("123");

        verify(schedulerService).schedule("123");
    }
}