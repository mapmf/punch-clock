package com.marcos.punchclock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.punchclock.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		
		if(!strategy.equals("create")) {
			return false;
		}
		
		dbService.populateDevDataBase();
		
		return true;
	}
	
}
