package com.loan.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PaymentDto {


	private Long paymentId;

	@NotNull(message="LOAN ID IS REQUIRED")
	private Long loanId;
	
	private LocalDate paymentDate;

	@NotNull(message="AMOUNT IS REQUIRED")
	@Positive(message="AMOUT MUST BE GREATER THAN ZERO.")
	private Double amount;

	@NotBlank(message="PAYMENT STATUS IS REQUIRED")
	@Size(max=25,message="STATUS CHARACTOR COUNT MUST NOT EXCEED 25.")
	private String paymentStatus;
	
	
	

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
