package com.loan.service;

import java.util.List;
import com.loan.dao.ApplicationReviewDAO;
import com.loan.entity.ApplicationReview;

public class ApplicationReviewService {

	ApplicationReviewDAO applicationReviewDAO = new ApplicationReviewDAO();

	// SUBMIT A REVIEW

	public void submitReview(ApplicationReview applicationReview) {

		applicationReviewDAO.save(applicationReview);

	}

	// UPDATE A REVIEW

	public void updateReview(ApplicationReview applicationReview) {

		applicationReviewDAO.update(applicationReview);

	}

	// DELETE A REVIEW

	public void deleteReview(ApplicationReview applicationReview) {

		applicationReviewDAO.delete(applicationReview);

	}

	// SEARCH A REVIEW

	public ApplicationReview searchReview(int reviewID) {

		return applicationReviewDAO.findById(reviewID);

	}

	// GET ALL REVIEWS

	public List<ApplicationReview> getAllApplicationReview() {

		return applicationReviewDAO.findAll();

	}

	// MAKE A DECISION

	public void makeDecision(String decision, ApplicationReview applicationReview) {

		applicationReview.setDecision(decision);

		applicationReviewDAO.update(applicationReview);
	}

}