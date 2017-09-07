package com.micahnoland.coolservice.dto;

public class GreetingDto {
	private String greeting;
	
	public GreetingDto(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
