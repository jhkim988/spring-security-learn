package com.ss.entity;

import lombok.Data;

@Data
public class Authority {
	private int id;
	private String name;
	private User user;
}
