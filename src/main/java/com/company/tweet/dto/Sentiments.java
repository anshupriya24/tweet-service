package com.company.tweet.dto;

public class Sentiments {
    String sentiment;

    public Sentiments(String sentiment) {
        this.sentiment = sentiment;
    }

    public Sentiments() {

    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
