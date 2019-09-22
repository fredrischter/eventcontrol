package com.creditsuisse.eventcontrol.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.creditsuisse.eventcontrol.model.EventData;
import com.creditsuisse.eventcontrol.model.EventLog;
import com.creditsuisse.eventcontrol.repository.EventDataRepository;
import com.creditsuisse.eventcontrol.repository.EventLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventProcessingService {
	
	@Autowired
	EventLogRepository eventLogRepository;
	
	@Autowired
	EventDataRepository eventDataRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void processEvents() throws IOException {
		
		InputStream inputStream = eventLogRepository.list();
		try (Scanner scanner = new Scanner(inputStream)) {

			while (scanner.hasNext()){
				process(scanner.nextLine());
			}
			
		}
		
	}

	private void process(String line) throws IOException {
		EventLog event = new ObjectMapper().readValue(line, EventLog.class);
		
		Optional<EventData> foundEventData = eventDataRepository.findById(event.getId());
		
		EventData eventData;
		if (foundEventData.isPresent()) {
			eventData = foundEventData.get();
			eventData.apply(event);
		} else {
			eventData = EventData.from(event);
		}
		
		eventDataRepository.save(eventData);
	}
	
	
}
