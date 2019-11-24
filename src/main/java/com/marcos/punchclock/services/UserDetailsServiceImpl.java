package com.marcos.punchclock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.config.SecurityConfig;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.security.UserSecurity;

/**
 * 
 * Implementation of UserDetailsService used to customize authentication
 * 
 * @see SecurityConfig
 * 
 * @author Marcos André
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private EmployeeService employeeService; 
	
	@Override
	public UserDetails loadUserByUsername(String pis) throws UsernameNotFoundException {
		
		Employee employee = employeeService.getById(pis);
		
		if(employee == null) {
			 throw new UsernameNotFoundException(pis);
		}
		
		return new UserSecurity(employee.getId(), employee.getPassword(), employee.getProfiles());
	}

}
