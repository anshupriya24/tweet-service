package com.company.tweet.dto;

import java.util.List;

import com.company.tweet.data.Tweet;

public class TweetDetails {
    String username;

    List<Tweet> tweetList;

    public TweetDetails() {

    }

    public String getUsername() {
        return username;
    }

    public TweetDetails(String username, List<Tweet> tweetList) {
        this.username = username;
        this.tweetList = tweetList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

}
