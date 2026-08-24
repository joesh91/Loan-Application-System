package com.loan.exception;

public class ApplicationReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationReviewNotFoundException(String message) {
		super(message);
	}

}
