package com.creditsuisse.eventcontrol.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.creditsuisse.eventcontrol.exception.InputNotDefinedException;

@Repository
public class EventLogRepository {
	
    @Autowired
    ApplicationArguments appArgs;
    
    @Value("${input}")
    String input;
    
	public InputStream list() throws InputNotDefinedException, FileNotFoundException {
		/*List<String> inputItems = appArgs.getOptionValues("input");
		
		if (inputItems == null || inputItems.isEmpty()) {
			throw new InputNotDefinedException();
		}*/
		
		//String input = input;//inputItems.get(0);
		
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
