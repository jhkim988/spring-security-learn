package com.ch04.sec01;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SAH512PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return hashWithSHA512(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return hashWithSHA512(rawPassword.toString()).equals(encodedPassword);
	}

	// 직접 구현해도 되지만, 기본으로 제공되는 옵션이 있다.
	private String hashWithSHA512(String input) {
		StringBuilder result = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digested = md.digest(input.getBytes());
			for (int i = 0; i < digested.length; i++) {
				result.append(Integer.toHexString(0xFF & digested[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Bad algorithm");
		}
		return result.toString();
	}
}
