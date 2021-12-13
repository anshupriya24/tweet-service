package com.company.tweet.dto;

public class TweetRequest {

	String text;
	String authorId;

	public TweetRequest(String text, String authorId) {
		super();
		this.text = text;
		this.authorId = authorId;
	}

	public TweetRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
}
