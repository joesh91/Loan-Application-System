package com.loan.service;

import java.util.ArrayList;
import java.util.List;
import com.loan.dao.ApplicationReviewDAO;
import com.loan.entity.ApplicationReview;
import com.loan.dto.ApplicationReviewDto;
import com.loan.entity.LoanApplication;
import com.loan.entity.Staff;
import com.loan.enums.ReviewDecision;
import com.loan.exception.ApplicationReviewNotFoundException;
import com.loan.exception.LoanApplicationNotFoundException;
import com.loan.exception.StaffNotFoundException;
import com.loan.dao.LoanApplicationDAO;
import com.loan.dao.StaffDAO;

public class ApplicationReviewService {

	ApplicationReviewDAO applicationReviewDAO = new ApplicationReviewDAO();

	// SUBMIT A REVIEW

	public void submitReview(ApplicationReviewDto applicationReviewDto) {
		
		LoanApplicationDAO loanApplicationDao = new LoanApplicationDAO();
		LoanApplication loanApplication = loanApplicationDao.findById(applicationReviewDto.getLoanApplication());
		
			if( loanApplication == null) {
				throw new LoanApplicationNotFoundException("LOAN APPLICATION "+ applicationReviewDto.getLoanApplication() +" IS NOT FOUND.");
			}
		
		StaffDAO staffDao = new StaffDAO();
		Staff staff = staffDao.findById(applicationReviewDto.getStaff());
		
			if(staff == null) {
				throw new StaffNotFoundException("STAFF ID "+ applicationReviewDto.getStaff() + " NOT FOUND.");
			}
		
		ApplicationReview applicationReview = new ApplicationReview();
		
		
		applicationReview.setLoanApplication(loanApplication);
		applicationReview.setStaff(staff);
		applicationReview.setComments(applicationReviewDto.getComments());
		applicationReview.setDecision(applicationReviewDto.getDecision().name());
		
		applicationReviewDAO.save(applicationReview);

	}

	// UPDATE A REVIEW


	public void updateReview(ApplicationReviewDto applicationReviewDto) {

		if(applicationReviewDto == null) {
			throw new ApplicationReviewNotFoundException("APPLICATION REVIEW IS NOT AVAILABLE.");
		}
		
		ApplicationReview existingApplicationReview = applicationReviewDAO.findById(applicationReviewDto.getReviewId());
		
		if( existingApplicationReview == null) {
			throw new ApplicationReviewNotFoundException("APPLICATION REVIEW " + applicationReviewDto.getReviewId() + " IS NOT FOUND.");
		}
		
		LoanApplicationDAO LoanApplicationDao = new LoanApplicationDAO();
		LoanApplication loanApplication = LoanApplicationDao.findById(applicationReviewDto.getLoanApplication());
		
		if(loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION " +applicationReviewDto.getLoanApplication() + " IS NOT FOUND.");
		}
		
		StaffDAO staffDao= new StaffDAO();
		Staff staff = staffDao.findById(applicationReviewDto.getStaff());
		
		if(staff == null) {
			throw new StaffNotFoundException("STAFF ID "+ applicationReviewDto.getStaff() + " NOT FOUND.");
		}
		
		existingApplicationReview.setLoanApplication(loanApplication);
		existingApplicationReview.setStaff(staff);
		existingApplicationReview.setDecision(applicationReviewDto.getDecision().name());
		existingApplicationReview.setComments(applicationReviewDto.getComments());
		
		applicationReviewDAO.update(existingApplicationReview);
	}

	
	// DELETE A REVIEW

	public void deleteReview(ApplicationReviewDto applicationReviewDto) {

		if(applicationReviewDto == null) {
			throw new ApplicationReviewNotFoundException ("APPLICATION REVIEW DATA IS NOT AVAILABLE");
		}
		
		ApplicationReview existingApplicationReview = applicationReviewDAO.findById(applicationReviewDto.getReviewId());
			
		if(existingApplicationReview == null) {
			throw new ApplicationReviewNotFoundException("APPLICATION REVIEW CONTENT IS NOT AVAILBLE IN THE DATABASE.");
		}
		
		applicationReviewDAO.delete(existingApplicationReview);
	}
	

	// SEARCH A REVIEW

	public ApplicationReviewDto searchReview(long reviewId) {
		
		ApplicationReview applicationReview = applicationReviewDAO.findById(reviewId);

			if(applicationReview == null) {
				throw new ApplicationReviewNotFoundException("REVIEW ID " +reviewId+ " IS NOT FOUND.");
			}
			
			ApplicationReviewDto applicationReviewDto = new ApplicationReviewDto();
			
			
			applicationReviewDto.setLoanApplication(applicationReview.getLoanApplication().getApplicationId());
			applicationReviewDto.setStaff(applicationReview.getStaff().getStaffId());
			applicationReviewDto.setComments(applicationReview.getComments());
			applicationReviewDto.setReviewId(applicationReview.getReviewId());
			applicationReviewDto.setDecision(ReviewDecision.valueOf(applicationReview.getDecision()));
			
			return applicationReviewDto;
	}

	// GET ALL REVIEWS

	public List<ApplicationReviewDto> getAllApplicationReview() {
		
		List<ApplicationReview> applicationReview = applicationReviewDAO.findAll();

		List<ApplicationReviewDto> applicationReviewDtos = new ArrayList<>();
		
		for(ApplicationReview a : applicationReview ) {
			
			ApplicationReviewDto applicationReviewDto = new ApplicationReviewDto();
			
			applicationReviewDto.setLoanApplication(a.getLoanApplication().getApplicationId());
			applicationReviewDto.setStaff(a.getStaff().getStaffId());
			applicationReviewDto.setReviewDate(a.getReviewDate());
			applicationReviewDto.setComments(a.getComments());
			applicationReviewDto.setDecision(ReviewDecision.valueOf(a.getDecision()));
			applicationReviewDto.setReviewId(a.getReviewId());
			
			applicationReviewDtos.add(applicationReviewDto);
		}				
		return applicationReviewDtos;
	}

	// MAKE A DECISION

	public void makeDecision(String decision, ApplicationReviewDto applicationReviewDto) {
		
		if(applicationReviewDto == null) {
			throw new ApplicationReviewNotFoundException("APPLICATION REVIEW DETAILS CANNOT BE EMPTY");
		}

		ApplicationReview applicationReview = applicationReviewDAO.findById(applicationReviewDto.getReviewId());
		
		if(applicationReview == null) {
			throw new ApplicationReviewNotFoundException("APPLICATION REVIEW DETAILS ARE NOT FOUND");
		}
		
		applicationReview.setDecision(decision);
		applicationReviewDAO.update(applicationReview);
	}

}