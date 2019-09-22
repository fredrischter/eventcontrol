package com.creditsuisse.eventcontrol.model;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
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
}
