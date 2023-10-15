package com.company.tweet.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.company.tweet.data.Tweet;

@Repository
public class TweetUserDALImpl implements TweetUserDAL {
	private final static Logger LOG = LoggerFactory.getLogger(TweetUserDALImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * This method is used to get users' tweet from mongodb
	 * 
	 * @param username This is the paramter which contain the username
	 * @return List<Tweet> This returns the tweetlist of the user.
	 */
	@Override
	public List<Tweet> getTweetsByUsername(String username) {
		LOG.info("[Inside getTweetsByUsername function]");
		Query query = new Query();
		query.addCriteria(Criteria.where("authorId").is(username));
		return mongoTemplate.find(query, Tweet.class);

	}

	/**
	 * This method is used to get users' tweet from mongodb as pages
	 * 
	 * @param username This is the paramter which contain the username
	 * @return Page<Tweet> This returns the tweetlist pages of the user.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Page<Tweet> getTweetsPageByUsername(String username, int page, int size) {
		LOG.info("[Inside getTweetsByUsername function]");
		Pageable pageable = PageRequest.of(page, size);
		Query query = new Query().with(pageable);
		query.addCriteria(Criteria.where("authorId").is(username));
		List<Tweet> list = mongoTemplate.find(query, Tweet.class);
		return PageableExecutionUtils.getPage(list, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Tweet.class));
	}

	/**
	 * This method is used to get the user from mongodb
	 * 
	 * @param username This is the paramter which contain username.
	 * @return Users This returns the users.
	 */
	@Override
	public Tweet getUserByUsername(String username) {
		LOG.info("[Inside getUserByUsername function]");
		Query query = new Query();
		query.addCriteria(Criteria.where("authorId").is(username));
		return mongoTemplate.findOne(query, Tweet.class);
	}

	/**
	 * This method is used to get other users from mongodb
	 * 
	 * @param username This is the paramter which contain username.
	 * @return List<Users> This returns the list of users.
	 */

	@Override
	public List<Tweet> getOtherUsersByUsername(String username) {
		LOG.info("[Inside getOtherUsersByUsername function]");
		Query query = new Query();
		query.addCriteria(Criteria.where("authorId").ne(username));
		return mongoTemplate.find(query, Tweet.class);
	}

	/**
	 * This method is used to get tweet by id from mongodb
	 * 
	 * @param id This is the paramter which contain id.
	 * @return Tweet This returns the tweet.
	 */

	@Override
	public Tweet getTweetById(String id) {
		LOG.info("[Inside getTweetById function]");
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Tweet.class);
	}

	/**
	 * This method is used to add tweet to mongodb
	 * 
	 * @param Tweet This is the paramter which contain tweet.
	 * @return Tweet This returns the tweet which is added.
	 */
	@Override
	public Tweet addNewTweet(Tweet tweet) {
		LOG.info("[Inside addNewTweet function]");
		final String uri = "http://51.20.1.154:5000/predict_sentiment";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri, tweet.getText(),String.class);
		Tweet tweet1 = new Tweet();
		tweet1.setText(tweet.getText() + "sentiments: " + result);
		tweet1.setAuthorId(tweet.getAuthorId());
		tweet1.setCreatedDate(tweet.getCreatedDate());
		tweet1.setId(tweet.getId());
		mongoTemplate.save(tweet);
		return tweet;
	}


}
