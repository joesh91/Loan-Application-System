package com.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanApplicationDto {

	
	
	private Long applicationId;

	@NotBlank(message="LOAN TYPE IS REQUIRED.")
	private String loanType;

	@NotNull(message="AMOUNT IS REQUIRED.")
	private BigDecimal requestedAmount;

	@NotBlank(message="PURPOSE IS REQUIRED.")
	private String purpose;

	@NotBlank(message="STATUS IS REQUIRED.")
	private String status;

	@NotNull(message="CUSTOMER ID IS REQUIRED.")
	private Long customerid;

	
	private LocalDateTime appDate;


	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getLoanType() {
		return loanType;
	}


	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}


	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}


	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getCustomerid() {
		return customerid;
	}


	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}


	public LocalDateTime getAppDate() {
		return appDate;
	}


	public void setAppDate(LocalDateTime appDate) {
		this.appDate = appDate;
	}

	
	
	

}
