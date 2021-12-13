package com.company.tweet.services;

import com.company.tweet.dto.Success;
import com.company.tweet.dto.TweetDetailsPageResponse;
import com.company.tweet.dto.TweetDetailsResponse;
import com.company.tweet.dto.TweetPageRequest;
import com.company.tweet.dto.TweetRequest;
import com.company.tweet.dto.TweetResponse;
import com.company.tweet.exceptions.InvalidDataException;

public interface TweetService {

	TweetResponse getMyTweets(String username) throws InvalidDataException;

	Success addTweet(TweetRequest tweet) throws InvalidDataException;

	TweetDetailsResponse getOthersTweet(String username) throws InvalidDataException;

	TweetDetailsPageResponse getOthersTweetPage(TweetPageRequest tweetPageRequest) throws InvalidDataException;

}
