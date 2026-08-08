package com.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "application_review")
public class ApplicationReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private long reviewId;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplication loanApplication;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@Column(name = "decision")
	private String decision;

	@Column(name = "comments")
	private String comments;

	@Column(name = "review_date", insertable = false)
	@CreationTimestamp
	private LocalDate reviewDate;

	// EMPTY CONSTRUCTOR

	public ApplicationReview() {

		// TODO Auto-generated constructor stub
	}

	// GETTERS AND SETTERS

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	public Staff getStaffId() {
		return staff;
	}

	public void setStaffId(Staff staffId) {
		this.staff = staffId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "ApplicationReview [reviewId=" + reviewId + ", loanApplication=" + loanApplication + ", staffId=" + staff
				+ ", decision=" + decision + ", comments=" + comments + ", reviewDate=" + reviewDate + "]";
	}

}
