package com.ss.dto;

import com.ss.entity.EncryptionAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Register {
	
	@Data
	public static class Request {
		private String username;
		private String password;
		private EncryptionAlgorithm algorithm;
	}
	
	@Data
	@AllArgsConstructor
	public static class Response {
		private boolean result;
		private String message;
	}
}
