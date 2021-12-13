package com.company.tweet.dto;

public class Success 
{
	String message;

	public Success(String message) {
		this.message = message;
	}

	public Success() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
