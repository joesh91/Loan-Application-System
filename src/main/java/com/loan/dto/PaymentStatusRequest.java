package com.loan.dto;

public class PaymentStatusRequest {

	private String decision;

	public PaymentStatusRequest() {

	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String request) {
		this.decision = request;
	}

	@Override
	public String toString() {
		return "PaymentStatusRequest [request=" + decision + "]";
	}
}
