package com.marcos.punchclock.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.marcos.punchclock.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	private static final String[] AUTHENTICATED_MATCHERS = {
			"/punch-clock/**",
			"/employee-work-days/**"
	};
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		
		http
			.httpBasic()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.authorizeRequests()
			.antMatchers(AUTHENTICATED_MATCHERS).authenticated()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.and()
			.formLogin();
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
