package com.company.tweet.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.company.tweet.constants.TweetConstants;
import com.company.tweet.dto.Problem;
import com.company.tweet.dto.TweetRequest;
import com.company.tweet.exceptions.InvalidDataException;

@Service
public class ValidationUtil {
	private final static Logger LOG = LoggerFactory.getLogger(ValidationUtil.class);

	/**
	 * This method is used to validate the input parameters of getting user tweet
	 */

	public List<Problem> validateMyTweets(String username) throws InvalidDataException {
		LOG.info("[Inside validateMyTweets function]");
		List<Problem> pBList = validateUsername(username);
		return pBList;
	}

	/**
	 * This method is used to validate the input parameters of adding the user
	 * tweet.
	 */

	public List<Problem> validateTweetBeforeAdd(TweetRequest tweetRequest) throws InvalidDataException {
		LOG.info("[Inside validateTweetBeforeAdd function]");
		List<Problem> pBList = validateUsername(tweetRequest.getAuthorId());
		List<Problem> pBListOther = validateText(tweetRequest.getText());
		pBListOther.addAll(pBList);
		return pBListOther;

	}

	/**
	 * This method is used to validate the input parameters of endpoint getting the
	 * others tweet.
	 */

	public List<Problem> validateOthersTweet(String username) throws InvalidDataException {
		LOG.info("[Inside validateOthersTweet function]");
		return validateUsername(username);
	}

	/**
	 * This method is used to validate the username.
	 */
	public List<Problem> validateUsername(String username) {
		LOG.info("[Inside validateUsername function]");
		List<Problem> pBList = new ArrayList<>();
		Problem pb = new Problem();
		if (Objects.isNull(username) || username.isEmpty()) {
			pb.setErrorMessage(TweetConstants.EMPTY_USERNAME_ERROR);
			pb.setErrorCode(TweetConstants.E202);
			pBList.add(pb);
		} else if (username.length() > TweetConstants.FIFTEEN) {
			pb = new Problem();
			pb.setErrorMessage(TweetConstants.USERNAME_INVALID_LEN_ERROR);
			pb.setErrorCode(TweetConstants.E203);
			pBList.add(pb);
		}
		return pBList;
	}

	/**
	 * This method is used to validate the tweet text.
	 */
	public List<Problem> validateText(String text) {
		LOG.info("[Inside validateText function]");
		List<Problem> pBList = new ArrayList<>();
		Problem pb = new Problem();
		if (Objects.isNull(text) || text.isEmpty()) {
			pb.setErrorMessage(TweetConstants.EMPTY_TEXT_ERROR);
			pb.setErrorCode(TweetConstants.E204);
			pBList.add(pb);
		} else if (text.length() > TweetConstants.ONE_SIXTY) {
			pb = new Problem();
			pb.setErrorMessage(TweetConstants.TEXT_INVALID_LEN_ERROR);
			pb.setErrorCode(TweetConstants.E205);
			pBList.add(pb);
		}
		return pBList;
	}

}
