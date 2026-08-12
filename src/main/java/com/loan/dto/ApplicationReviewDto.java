package com.loan.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApplicationReviewDto {
	
	
	private Long reviewId;

	@NotNull(message="LOAN APPLICATION ID IS REQUIRED.")
	private Long loanApplication;

	@NotNull(message="STAFF ID IS REQUIRED.")
	private Long staff;

	@NotBlank(message="DECISION IS REQUIRED.")
	private String decision;

	@NotBlank(message="COMMETNS ARE REQUIRED.")
	private String comments;

	private LocalDate reviewDate;
	
	
	
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}


	public Long getLoanApplication() {
		return loanApplication;
	}
	

	public void setLoanApplication(Long loanApplication) {
		this.loanApplication = loanApplication;
	}

	public Long getStaff() {
		return staff;
	}

	public void setStaff(Long i) {
		this.staff = i;
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


}
