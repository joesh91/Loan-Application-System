package com.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanDto {

	private Long loanId;

	@NotNull(message="APPLICATION ID IS REQUIRED.")
	private Long applicationId;

	@NotNull(message="AMOUNT IS REQUIRED.")
	private BigDecimal appAmount;

	@NotNull(message="INTEREST RATE IS REQUIRED.")
	private double intRate;

	@NotNull(message="DURATION IS REQUIRED.")
	private Long duration;

	
	private LocalDate startDate;


	private LocalDate endDate;

	@NotBlank(message="STATUS IS REQUIRED.")
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

	public double getIntRate() {
		return intRate;
	}

	public void setIntRate(double intRate) {
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
