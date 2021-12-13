package com.company.tweet.dto;

public class TweetPageRequest {

	String username;
	String page;
	String size;

	public TweetPageRequest() {
	}

	public TweetPageRequest(String username, String page, String size) {
		this.username = username;
		this.page = page;
		this.size = size;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
