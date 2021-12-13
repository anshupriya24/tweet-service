package com.company.tweet.dto;
import java.io.Serializable;
import java.util.List;

public class ProblemResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Problem> problemList;

	public ProblemResponse() {
		super();
	}

	public ProblemResponse(List<Problem> problemList) {
		this.problemList = problemList;
	}

	public List<Problem> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}
}

