package com.ss.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {
	
	private GenerateCodeUtil() { }
	
	public static String generateCode() {
		try {
			SecureRandom random = SecureRandom.getInstanceStrong();
			int c = random.nextInt(9000) + 1000;
			return String.valueOf(c);			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Problem when generating the random code.");
		}
	}
}
