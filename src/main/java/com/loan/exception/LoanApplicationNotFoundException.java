package com.loan.exception;

public class LoanApplicationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoanApplicationNotFoundException(String message) {
		super(message);
	}

}
