package com.marcos.punchclock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.punchclock.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		
		dbService.populateTestDataBase();
		
		return true;
	}
	
}