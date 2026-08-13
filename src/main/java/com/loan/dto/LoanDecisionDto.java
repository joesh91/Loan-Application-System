package com.loan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanDecisionDto {
	
	@NotNull(message="LOAN IS IS REQUIRED")
	private Long loanId;
	
	@NotBlank(message="STATUS IS REQUIRED")
	private String status;
	
	
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
		

}
