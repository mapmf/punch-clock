package com.marcos.punchclock.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.security.UserSecurity;

@Service
public class UserService {

	public UserDetails authenticated() {
		
		try {
			return (UserSecurity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
		
	}
}
