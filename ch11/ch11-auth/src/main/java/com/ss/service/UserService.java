package com.ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.entity.Otp;
import com.ss.entity.User;
import com.ss.repository.OtpRepository;
import com.ss.repository.UserRepository;
import com.ss.util.GenerateCodeUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtpRepository otpRepository;

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void auth(User user) {
		User u = userRepository
					.findUserByUsername(user.getUsername())
					.orElseThrow(() -> new BadCredentialsException("Bad Credentials."));
		if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
			throw new BadCredentialsException("Bad Credentials");
		}
		renewOtp(u);
	}

	private void renewOtp(User u) {
		String code = GenerateCodeUtil.generateCode();
		Otp otp = otpRepository.findOtpByUsername(u.getUsername()).orElseGet(() -> new Otp());
		otp.setUsername(u.getUsername());
		otp.setCode(code);
		otpRepository.save(otp);
	}
	
	public boolean check(Otp otpToValidate) {
		return otpRepository
				.findOtpByUsername(otpToValidate.getUsername())
				.filter(otp -> otp.getCode().equals(otpToValidate.getCode()))
				.isPresent();
	}
}
