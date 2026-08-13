package com.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LoanDto {
	
	
	private Long loanId;

	@NotNull(message="APPLICATION ID IS REQUIRED.")
	@Positive(message="APPLICATION ID SHOULD NOT CONTAIN NEGATIVE VALUE ")
	private Long applicationId;

	@NotNull(message="AMOUNT IS REQUIRED.")
	@Positive(message="AMOUNT MUST BE GREATER THAN ZERO.")
	private BigDecimal appAmount;

	@NotNull(message="INTEREST RATE IS REQUIRED.")
	@Positive(message="INTEREST RATE MUST BE GREATER THAN ZERO.")
	private Double intRate;

	@NotNull(message="DURATION IS REQUIRED.")
	@Positive(message="DURATION MUST BE GREATER THAN ZERO.")
	private Long duration;


	private LocalDate startDate;


	private LocalDate endDate;

	@NotBlank(message="STATUS IS REQUIRED.")
	@Size(max=25,message="STATUS CHARACTOR COUNT MUST NOT EXCEED 25.")
	private String status;

	
	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public BigDecimal getAppAmount() {
		return appAmount;
	}

	public void setAppAmount(BigDecimal appAmount) {
		this.appAmount = appAmount;
	}

	public Double getIntRate() {
		return intRate;
	}

	public void setIntRate(Double intRate) {
		this.intRate = intRate;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
