package com.company.tweet.dto;

import java.io.Serializable;

public class ValidateTokenRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String token;
	private String username;
	
	public ValidateTokenRequest(String token, String username) {
		this.token = token;
		this.username = username;
	}
	
	public ValidateTokenRequest() {
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
