package com.ss.entity;

public enum EncryptionAlgorithm {
	BCRYPT("BCRYPT"), SCRYPT("SCRYPT");
	private String algorithm;
	
	EncryptionAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String toString() {
		return algorithm;
	}
}
