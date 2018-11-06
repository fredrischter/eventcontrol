package com.creditsuisse.eventcontrol.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class EventLogRepository {
	
    @Autowired
    ApplicationArguments appArgs;
    
    @Value("${input}")
    String input;
    
	public InputStream list() throws FileNotFoundException {
		
		InputStream inputStream;
		
		try {
			
			ClassPathResource resource = new ClassPathResource(input);
			inputStream = resource.getInputStream();
			
		} catch (Exception e) {

			File inputFile = new File(input);

			if (!inputFile.exists() || !inputFile.isFile()) {
				throw new FileNotFoundException(input);
			}
			
			inputStream = new FileInputStream(inputFile);
			
		}
		
		return inputStream;
	}
}
