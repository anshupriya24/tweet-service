package com.company.tweet.dto;

public class Sentiment {
    String text;

    public Sentiment(String text) {
        this.text = text;
    }

    public Sentiment() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
