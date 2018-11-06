package com.creditsuisse.eventcontrol.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EventData {

	@Id
	Integer id;
	
	Long duration;
	
	String type;
	
	String host;
	
	String alert;
	
}
