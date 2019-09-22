package com.creditsuisse.eventcontrol.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class EventData {

	@Transient
	private final Logger logger = LoggerFactory.getLogger(EventData.class);
	private static final int TIME_TOLERANCE = 4;
	private static final String GENERATED_ALERT_FOR = "Generated alert for ";

	@Id
	String id;
	
	Long start;
	
	Long end;
	
	String type;
	
	String host;
	
	Boolean alert;

	public EventData() {
	}

	public EventData(String id, Long start, Long end, String type, String host, Boolean alert) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	public static EventData from(EventLog event) {
		return new EventData(event.getId(),
			event.getStart(),
			event.getEnd(),
			event.getType(),
			event.getHost(),
			null);
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
			if (duration > TIME_TOLERANCE) {
				setAlert(true);
				logger.info(GENERATED_ALERT_FOR+this);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}
}
