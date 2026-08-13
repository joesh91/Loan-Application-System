package com.loan.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "LOAN")
public class Loan {

	@Id
	@Column(name = "LOAN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	@OneToOne
	@JoinColumn(name = "APPLICATION_ID")
	private LoanApplication application;

	@Column(name = "APPROVED_AMOUNT")
	private BigDecimal appAmount;

	@Column(name = "INTEREST_RATE")
	private Double intRate;

	@Column(name = "DURATION_MONTH")
	private Long duration;

	@Column(name = "START_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "STATUS")
	private String status;

	public Loan() {

		// TODO Auto-generated constructor stub
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public LoanApplication getApplication() {
		return application;
	}

	public void setApplication(LoanApplication applicationId) {
		this.application = applicationId;
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

	public void setDuration(Long durationM) {
		this.duration = durationM;
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

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", applicationId=" + application + ", appAmount=" + appAmount + ", intRate="
				+ intRate + ", durationM=" + duration + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", statud=" + status + "]";
	}

}