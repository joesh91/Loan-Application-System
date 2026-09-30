package com.loan.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.loan.enums.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class PaymentDto {

	private Long paymentId;

	@NotNull(message = "LOAN ID IS REQUIRED")
	@Positive(message = "LOAN ID SHOULD NO CONTAIN NEGATIVE VALUES")
	private Long loanId;

	//@NotNull(message="PAYMENT DATE CANNOT BE EMPTY")
	@JsonSerialize(using = LocalDateSerializer.class)			// A serializer's job is: "How should this Java object be written into JSON?"
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate paymentDate;

	@NotNull(message = "AMOUNT IS REQUIRED")
	@Positive(message = "AMOUT MUST BE GREATER THAN ZERO.")
	private Double amount;

	private PaymentStatus paymentStatus;

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

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



}
