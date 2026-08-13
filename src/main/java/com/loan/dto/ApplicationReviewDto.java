package com.loan.dto;

import java.time.LocalDate;
import com.loan.enums.ReviewDecision;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationReviewDto {
	
	
	private Long reviewId;

	@NotNull(message="LOAN APPLICATION ID IS REQUIRED.")
	private Long loanApplication;

	@NotNull(message="STAFF ID IS REQUIRED.")
	private Long staff;

	@NotNull(message="DECISION IS REQUIRED.")
	private ReviewDecision decision;

	@NotBlank(message="COMMENTS ARE REQUIRED.")
	@Size(max=500,message="COMMENTS CANNOT EXCEED 500 CHARACTERS.")
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

	public ReviewDecision getDecision() {
		return decision;
	}

	public void setDecision(ReviewDecision decision) {
		this.decision = decision;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


}
