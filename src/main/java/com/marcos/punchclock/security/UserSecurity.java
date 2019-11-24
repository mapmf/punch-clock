package com.marcos.punchclock.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcos.punchclock.model.enums.Profile;

/**
 * This class is a UserDetails implementation. 
 * That is useful to get a Employee object and convert
 * to a user that Spring Boot knows
 * 
 * @author Marcos André
 *
 */

public class UserSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSecurity(String username, String password, Set<Profile> profiles) {

		this.username = username;
		this.password = password;

		authorities = profiles.stream().map(p -> new SimpleGrantedAuthority(p.getRole())).collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
