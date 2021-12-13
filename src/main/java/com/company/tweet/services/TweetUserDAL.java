package com.company.tweet.services;

import com.company.tweet.data.Tweet;

import java.util.List;

import org.springframework.data.domain.Page;

public interface TweetUserDAL {
	List<Tweet> getTweetsByUsername(String username);

	List<Tweet> getOtherUsersByUsername(String username);

	Tweet getTweetById(String id);

	Tweet addNewTweet(Tweet tweet);

	Tweet getUserByUsername(String username);

	Page<Tweet> getTweetsPageByUsername(String username, int page, int size);
}
