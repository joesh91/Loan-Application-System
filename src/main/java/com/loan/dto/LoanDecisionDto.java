package com.loan.dto;

import com.loan.enums.LoanStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class LoanDecisionDto {

	@NotNull(message = "LOAN IS IS REQUIRED")
	private Long loanId;

	//@NotBlank(message = "STATUS IS REQUIRED")
	//private String status;
	
	
	@Enumerated(EnumType.STRING)
	private LoanStatus status;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}
	
	



}
