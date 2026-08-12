package com.loan.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "LOAN_APPLICATION")
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APPLICATION_ID")
	private Long applicationId;

	@Column(name = "LOAN_TYPE")
	private String loanType;

	@Column(name = "REQUESTED_AMOUNT")
	private BigDecimal requestedAmount;

	@Column(name = "PURPOSE")
	private String purpose;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "APPLICATION_DATE", updatable = false)
	@CreationTimestamp
	private LocalDateTime appDate;

	public LoanApplication() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getAppDate() {
		return appDate;
	}

	public void setAppDate(LocalDateTime appDate) {
		this.appDate = appDate;
	}

	@Override
	public String toString() {
		return "Loan_Application [applicationId=" + applicationId + ", loanType=" + loanType + ", requestedAmount="
				+ requestedAmount + ", purpose=" + purpose + ", status=" + status + ", customer=" + customer
				+ ", appDate=" + appDate + "]";
	}

}