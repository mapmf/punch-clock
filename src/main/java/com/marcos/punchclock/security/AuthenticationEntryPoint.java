package com.marcos.punchclock.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * This classe handles authentication exception
 * 
 * @author Marcos André
 *
 */

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        
        PrintWriter writer = response.getWriter();
        
        writer.println("HTTP status 401 - " + authException.getMessage());
	}
	
	@Override
    public void afterPropertiesSet() {
		
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }

}
