package com.company.tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TweetServiceApplication {
    public static void main(String[] args) {
    	final Logger LOG = LoggerFactory.getLogger(TweetServiceApplication.class);
    	LOG.info("[Inside  TweetServiceApplication");
        SpringApplication.run(TweetServiceApplication.class, args);
    }
}
