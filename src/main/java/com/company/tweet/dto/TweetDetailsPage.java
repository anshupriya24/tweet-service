package com.company.tweet.dto;

import org.springframework.data.domain.Page;

import com.company.tweet.data.Tweet;

public class TweetDetailsPage {
	String username;

	Page<Tweet> tweetList;

	public TweetDetailsPage() {

	}

	public TweetDetailsPage(String username, Page<Tweet> tweetList) {
		this.username = username;
		this.tweetList = tweetList;
	}

	public Page<Tweet> getTweetList() {
		return tweetList;
	}

	public void setTweetList(Page<Tweet> tweetList) {
		this.tweetList = tweetList;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
