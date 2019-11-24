package com.marcos.punchclock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.punchclock.services.DBService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
				
		dbService.populateProdDatabase();
		
		return true;
	}
}
