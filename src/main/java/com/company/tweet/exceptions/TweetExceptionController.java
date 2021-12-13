package com.company.tweet.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.company.tweet.dto.ProblemResponse;

@ControllerAdvice
public class TweetExceptionController {
	private final static Logger LOG = LoggerFactory.getLogger(TweetExceptionController.class);
	/**
	 * This method is used to catch the exception when an invalid input is passed
	 */
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ProblemResponse> invalidTextException(InvalidDataException ex) {
		LOG.info("[Inside invalidTextException function]");
		return new ResponseEntity<>(ex.getProblemResponse(), HttpStatus.BAD_REQUEST);
	}

}
