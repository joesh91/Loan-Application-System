package com.loan.dto;

import jakarta.validation.constraints.NotBlank;

public class PaymentStatusRequest {

	@NotBlank(message="DECISION IS REQUIRED")
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
