package com.ss.setting;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SimpleUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails u = new SimpleSecurityUser();
		if (u.getUsername().equals(username)) return u;
		else throw new UsernameNotFoundException("WRONG");
	}

}
