package com.company.tweet.dto;

public class Problem {
	String errorMessage;

    public Problem() {

    }

    public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	String errorCode;

	public Problem(String errorMessage, String errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
