package com.company.tweet.exceptions;

import com.company.tweet.dto.ProblemResponse;

public class InvalidDataException extends TweetException{

	/**
	 *
	 */
	private static final long serialVersionUID = -6801665702748427735L;

	ProblemResponse problemResponse = new ProblemResponse();

	public InvalidDataException(String message , ProblemResponse problemResponse) {
		super(message);
		this.problemResponse = problemResponse;
	}

	public ProblemResponse getProblemResponse() {
		return problemResponse;
	}

	public void setProblemResult(ProblemResponse problemResponse) {
		this.problemResponse = problemResponse;
	}



}
