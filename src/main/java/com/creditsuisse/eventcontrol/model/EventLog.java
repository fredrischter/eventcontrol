package com.creditsuisse.eventcontrol.model;

import lombok.Data;

@Data
public class EventLog {

	String id;
	
	String state;
	
	Long timestamp;
	
	String type;
	
	String host;
	
}
