package com.company.tweet.dto;
import java.io.Serializable;
import java.util.List;

import com.company.tweet.data.Tweet;

public class TweetResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tweet> tweetList;

	public TweetResponse() {
		super();
	}

	public TweetResponse(List<Tweet> tweetList) {
		this.tweetList = tweetList;
	}

	public List<Tweet> getTweetList() {
		return tweetList;
	}

	public void setTweetList(List<Tweet> tweetList) {
		this.tweetList = tweetList;
	}
}

