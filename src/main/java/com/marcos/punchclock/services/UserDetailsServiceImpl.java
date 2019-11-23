package com.marcos.punchclock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private EmployeeService employeeService; 
	
	@Override
	public UserDetails loadUserByUsername(String pis) throws UsernameNotFoundException {
		
		Employee employee = employeeService.getByPis(pis);
		
		if(employee == null) {
			 throw new UsernameNotFoundException(pis);
		}
		
		return new UserSecurity(employee.getId(), employee.getPassword(), employee.getProfiles());
	}

}
