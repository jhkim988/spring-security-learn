package com.ss.entity;

public enum Currency {
	USD("USD"), GBP("GBP"), EUR("EUR");

	private String name;
	
	Currency(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
