package com.loan.dto;

import com.loan.enums.ReviewDecision;

public class LoanApplicationReviewDecision {
	
	private ReviewDecision decision;

	public ReviewDecision getDecision() {
		return decision;
	}

	public void setDecision(ReviewDecision decision) {
		this.decision = decision;
	}

	@Override
	public String toString() {
		return "LoanApplicationReviewDecision [decision=" + decision + "]";
	}
	
	
	
	

}
