package com.loan.test;

import com.loan.service.ApplicationReviewService;
import com.loan.service.LoanApplicationService;
import com.loan.service.StaffService;

import java.time.LocalDate;

import com.loan.dao.ApplicationReviewDAO;
import com.loan.entity.ApplicationReview;
import com.loan.entity.LoanApplication;
import com.loan.entity.Staff;

public class ApplicationReviewServiceTest {
	public static void main(String[] args) {
		ApplicationReviewService applicationReviewService = new ApplicationReviewService();

		ApplicationReview applicationReview = new ApplicationReview();

		System.out.println(applicationReviewService.searchReview(1));

	}
}