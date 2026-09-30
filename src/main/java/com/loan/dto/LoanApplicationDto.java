package com.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.loan.enums.LoanApplicationStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LoanApplicationDto {

	private Long applicationId;

	@NotBlank(message = "LOAN TYPE IS REQUIRED.")
	private String loanType;

	@NotNull(message = "AMOUNT IS REQUIRED.")
	@Positive(message = "AMOUNT MUST BE GREATER THAN ZERO")
	private BigDecimal requestedAmount;

	@NotBlank(message = "PURPOSE IS REQUIRED.")
	private String purpose;

	@Enumerated(EnumType.STRING)
	private LoanApplicationStatus status;


	private Long customerid;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime appDate;
	
	
	public LoanApplicationDto() {
		
		// TODO Auto-generated constructor stub
	}

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
	
	public LoanApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(LoanApplicationStatus status) {
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
