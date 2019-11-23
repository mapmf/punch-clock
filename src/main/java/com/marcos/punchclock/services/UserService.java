package com.marcos.punchclock.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcos.punchclock.security.UserSecurity;

public class UserService {

	public UserDetails authenticated() {
		
		try {
			return (UserSecurity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
		
	}
}
