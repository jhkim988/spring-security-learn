package com.ss.sec01;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private String authority;
}
