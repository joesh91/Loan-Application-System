package com.loan.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@ManyToOne
	@JoinColumn(name = "loanId")
	private Loan loan;

	@Column(name = "payment_date", updatable = false)
	@CreationTimestamp
	private LocalDate paymentDate;

	@Column(name = "amount")
	private double amount;

	@Column(name = "payment_status")
	private String paymentStatus;

	public Payment() {

		// TODO Auto-generated constructor stub
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentId + ", loanId=" + loan + ", paymentDate=" + paymentDate + ", amount="
				+ amount + ", paymentStatus=" + paymentStatus + "]";
	}

}
