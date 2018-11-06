package com.creditsuisse.eventcontrol.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@ToString
public class EventData {

	@Id
	String id;
	
	Long start;
	
	Long end;
	
	String type;
	
	String host;
	
	Boolean alert;

	public static EventData from(EventLog event) {
		return EventData.builder()
				.id(event.getId())
				.type(event.getType())
				.host(event.getHost())
				.start(event.getStart())
				.end(event.getEnd())
				.build();
	}

	public void apply(EventLog event) {
		if (event.isStart()) {
			setStart(event.getStart());
		}
		if (event.isEnd()) {
			setEnd(event.getEnd());
		}
		if (getStart() != null && getEnd() != null) {
			long duration = getEnd() - getStart();
			if (duration > 4) {
				setAlert(true);
				log.info("Generated alert for "+this);
			}
		}
	}
	
}
