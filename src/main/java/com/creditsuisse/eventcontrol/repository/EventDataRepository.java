package com.creditsuisse.eventcontrol.repository;

import org.springframework.data.repository.CrudRepository;
import com.creditsuisse.eventcontrol.model.EventData;

public interface EventDataRepository extends CrudRepository<EventData, String> {

}
