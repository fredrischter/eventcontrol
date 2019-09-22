package com.creditsuisse.eventcontrol.service;

import com.creditsuisse.eventcontrol.model.EventData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class EventProcessingServiceTest {

    @Autowired
    EventProcessingService eventProcessingService;

    @Test
    public void persistenceTest() {
        // Given
        Iterable<EventData> events = eventProcessingService.getEvents();
        Map<String, List<EventData>> employeesMap = StreamSupport.stream(events.spliterator(), true)
            .collect(Collectors.groupingBy(EventData::getId));

        // Then
        assertThat(events).hasSize(4);
        assertThat(employeesMap).containsKeys("scsmbstgra", "scsmbstgrb", "scsmbstgrc");
    }
}
