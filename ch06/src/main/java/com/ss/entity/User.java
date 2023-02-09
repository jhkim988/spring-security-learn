package com.ss.entity;

import java.util.List;

import lombok.Data;

@Data
public class User {
	private int id;
	private String username;
	private String password;
	private EncryptionAlgorithm algorithm;
}
