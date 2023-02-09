package com.ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ss.entity.User;
import com.ss.repository.AuthorityRepository;
import com.ss.repository.UserRepository;
import com.ss.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credential"));
		UserDetails userDetails = new CustomUserDetails(u, authorityRepository.findByUser(u));
		return userDetails;
	}

}
