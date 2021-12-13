package com.company.tweet.dto;
import java.util.List;

public class TweetDetailsResponse {

	private List<TweetDetails> tweetDetailsList;

	public TweetDetailsResponse(List<TweetDetails> tweetDetailsList) {
		this.tweetDetailsList = tweetDetailsList;
	}

	public TweetDetailsResponse() {
	}

	public List<TweetDetails> getTweetDetailsList() {
		return tweetDetailsList;
	}
}

