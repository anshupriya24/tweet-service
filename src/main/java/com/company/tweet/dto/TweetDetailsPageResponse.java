package com.company.tweet.dto;
import java.util.List;

public class TweetDetailsPageResponse {

	private List<TweetDetailsPage> tweetDetailsPage;

	public TweetDetailsPageResponse(List<TweetDetailsPage> tweetDetailsPage) {
		this.tweetDetailsPage = tweetDetailsPage;
	}

	public List<TweetDetailsPage> getTweetDetailsPage() {
		return tweetDetailsPage;
	}

	public void setTweetDetailsPage(List<TweetDetailsPage> tweetDetailsPage) {
		this.tweetDetailsPage = tweetDetailsPage;
	}


}

