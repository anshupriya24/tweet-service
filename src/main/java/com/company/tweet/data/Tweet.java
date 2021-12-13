package com.company.tweet.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tweet")
public class Tweet {
	@Id
	String id;
	String text;
	String authorId;
	String createdDate;

	public Tweet(String id, String text, String authorId, String createdDate) {
		super();
		this.id = id;
		this.text = text;
		this.authorId = authorId;
		this.createdDate = createdDate;
	}

	public Tweet() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
}
