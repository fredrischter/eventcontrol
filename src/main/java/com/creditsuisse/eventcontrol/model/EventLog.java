package com.creditsuisse.eventcontrol.model;

import lombok.Data;

@Data
public class EventLog {

	private static final String FINISHED = "FINISHED";

	private static final String STARTED = "STARTED";

	String id;
	
	String state;
	
	Long timestamp;
	
	String type;
	
	String host;

	public boolean isStart() {
		return STARTED.equals(getState());
	}

	public boolean isEnd() {
		return FINISHED.equals(getState());
	}

	public Long getStart() {
		if (isStart())
			return timestamp;
		return null;
	}

	public Long getEnd() {
		if (isEnd())
			return timestamp;
		return null;
	}
	
}
