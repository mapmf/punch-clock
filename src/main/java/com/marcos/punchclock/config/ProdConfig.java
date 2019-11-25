package com.marcos.punchclock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.punchclock.services.DBService;

/**
 * This class manages beans that will 
 * initialized with production profile
 *
 * @author Marcos André
 */

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() {
		
		if(!strategy.equals("create") && !strategy.equals("update")) {
			return false;
		}
		
		dbService.populateProdDatabase();
		
		return true;
	}
}
