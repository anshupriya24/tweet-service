package com.company.tweet.services;

import com.company.tweet.constants.TweetConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.company.tweet.util.ValidationUtil;
import com.company.tweet.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import com.company.tweet.data.Tweet;
import org.springframework.http.MediaType;
import com.company.tweet.exceptions.InvalidDataException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class TweetServiceImpl implements TweetService {
	private final static Logger LOG = LoggerFactory.getLogger(TweetServiceImpl.class);

	@Autowired
	ValidationUtil validationUtil;

	@Autowired
	TweetUserDAL tweetUserDAL;

	/**
	 * This method is used to get the user's tweet whose name is passed in the
	 * request object.
	 * 
	 * @param username This is the paramter to getMyTweets method
	 * @return TweetResponse This returns the authenticationToken and the username.
	 */

	public TweetResponse getMyTweets(String username) throws InvalidDataException {
		LOG.info("[Inside getMyTweets function]");
		List<Tweet> tweetList = new ArrayList<>();
		List<Problem> pBList = validationUtil.validateMyTweets(username);
		if (pBList.isEmpty()) {
			tweetList = tweetUserDAL.getTweetsByUsername(username);
		} else {
			throw new InvalidDataException(TweetConstants.INVALID_USER_ERROR, new ProblemResponse(pBList));
		}
		return new TweetResponse(tweetList);
	}

	/**
	 * This method is used to add the user's tweet
	 * 
	 * @param username This is the paramter which contain tweet to be added
	 * @return Success This returns the successful message of insertion.
	 */

	public Success addTweet(TweetRequest tweetRequest) throws InvalidDataException {
        LOG.info("[Inside addTweet function]");
        Success success = new Success();
        final String uri = "http://16.170.228.195:5000/predict_sentiment";
        Tweet tweet = new Tweet();
        RestTemplate restTemplate = new RestTemplate();
        Sentiment sentiment = new Sentiment();
        sentiment.setText(tweetRequest.getText());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json="";
        try {
             json = ow.writeValueAsString(sentiment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<String>(json,headers);
        Sentiments responseEntity = restTemplate.postForObject(uri,request, Sentiments.class);
        //List<Problem> pBListOther = validationUtil.validateTweetBeforeAdd(tweetRequest);
        //if (pBListOther.isEmpty()) {
        tweet.setAuthorId(tweetRequest.getAuthorId());
        tweet.setCreatedDate(new Date().toString());
        tweet.setText(tweetRequest.getText() + " :sentiments: " + responseEntity.getSentiment());
        Tweet tweetOut = tweetUserDAL.addNewTweet(tweet);
        if (null != tweetOut) {
            success.setMessage("Tweet inserted successfully");
        }
        //} else {
        //	throw new InvalidDataException(TweetConstants.INVALID_USER_ERROR, new ProblemResponse(pBListOther));
        //}

        return success;

    }

	/**
	 * This method is used to get other users tweet
	 * 
	 * @param username This is the paramter which contain the username
	 * @return TweetDetailsResponse This returns the userlist and their tweets
	 */
	public TweetDetailsResponse getOthersTweet(String username) throws InvalidDataException {
		LOG.info("[Inside getOthersTweet function]");
		TweetDetails details;
		List<TweetDetails> detailsList = new ArrayList<>();
		List<Problem> pBList = validationUtil.validateOthersTweet(username);
		if (pBList.isEmpty()) {
			List<Tweet> tweetList = tweetUserDAL.getOtherUsersByUsername(username);
			Set<String> userList = new HashSet<>();
			for (Tweet tweet : tweetList) {
				userList.add(tweet.getAuthorId());
			}
			for (String user : userList) {
				details = new TweetDetails();
				List<Tweet> tweetlist = tweetUserDAL.getTweetsByUsername(user);
				details.setTweetList(tweetlist);
				details.setUsername(user);
				detailsList.add(details);
			}

		} else {
			throw new InvalidDataException(TweetConstants.INVALID_USER_ERROR, new ProblemResponse(pBList));
		}
		return new TweetDetailsResponse(detailsList);
	}

	/**
	 * This method is used to get other users tweet(paginaton is implemented here)
	 * 
	 * @param username This is the paramter which contain the username
	 * @return TweetDetailsResponse This returns the userlist and their tweets
	 */
	public TweetDetailsPageResponse getOthersTweetPage(TweetPageRequest tweetPageRequest) throws InvalidDataException {
		LOG.info("[Inside getOthersTweet function]");
		TweetDetailsPage details;
		List<TweetDetailsPage> detailsList = new ArrayList<>();
		List<Problem> pBList = validationUtil.validateOthersTweet(tweetPageRequest.getUsername());
		if (pBList.isEmpty()) {
			List<Tweet> tweetList = tweetUserDAL.getOtherUsersByUsername(tweetPageRequest.getUsername());
			Set<String> userList = new HashSet<>();
			for (Tweet tweet : tweetList) {
				userList.add(tweet.getAuthorId());
			}
			for (String user : userList) {
				details = new TweetDetailsPage();
				Page<Tweet> tweetlist = tweetUserDAL.getTweetsPageByUsername(user,
						Integer.parseInt(tweetPageRequest.getPage()), Integer.parseInt(tweetPageRequest.getSize()));
				details.setTweetList(tweetlist);
				details.setUsername(user);
				detailsList.add(details);
			}

		} else {
			throw new InvalidDataException(TweetConstants.INVALID_USER_ERROR, new ProblemResponse(pBList));
		}
		return new TweetDetailsPageResponse(detailsList);
	}

}
