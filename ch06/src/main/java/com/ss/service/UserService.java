package com.ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.dto.Register;
import com.ss.entity.EncryptionAlgorithm;
import com.ss.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean register(Register.Request request) {
		request.setPassword(encoder.encode(request.getPassword()));
		request.setAlgorithm(EncryptionAlgorithm.BCRYPT);
		return userRepository.insert(request);
	}
}
