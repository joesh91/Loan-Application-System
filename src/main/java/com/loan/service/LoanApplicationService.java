package com.loan.service;

import java.util.ArrayList;
import java.util.List;

import com.loan.dao.CustomerDAO;
import com.loan.dao.LoanApplicationDAO;
import com.loan.dao.UserDAO;
import com.loan.dto.LoanApplicationDto;
import com.loan.entity.Customer;
import com.loan.entity.LoanApplication;
import com.loan.entity.User;
import com.loan.exception.CustomerNotFoundException;
import com.loan.exception.LoanApplicationNotFoundException;
import com.loan.exception.UserNotFoundException;

public class LoanApplicationService {

	LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();
	


	// SUBMIT A LOAN APPLICATION

	public void submitLoanApplication(LoanApplicationDto loanApplicationDto ,String userName) {
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserName(userName);
		
		
		if(user == null) {
			throw new UserNotFoundException("USER NOT FOUND");
		}
		

		if (loanApplicationDto == null) {
			throw new LoanApplicationNotFoundException("INCOMPLETE LOAN APPLICATION DETAILS.");
		}

		LoanApplication loanApplication = new LoanApplication();

		Customer customer = user.getCustomer();

		if (customer == null) {
			throw new CustomerNotFoundException("CUSTOMER ID " + customer.getCustomerId() + " IS NOT FOUND.");
		}

		loanApplication.setCustomer(customer);
		loanApplication.setLoanType(loanApplicationDto.getLoanType());
		loanApplication.setPurpose(loanApplicationDto.getPurpose());
		loanApplication.setRequestedAmount(loanApplicationDto.getRequestedAmount());
		loanApplication.setStatus("PENDING");

		loanApplicationDAO.save(loanApplication);
		
		loanApplicationDto.setApplicationId(loanApplication.getApplicationId());
	}

	// UPDATE A LOAN APPLICATION

	public void updateLoanApplication(LoanApplicationDto loanApplicationDto) {
		
		
		
		if (loanApplicationDto == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION DETAILS CANNOT EB EMPTY.");
		}
		
		
		LoanApplication loanApplication = loanApplicationDAO.findById(loanApplicationDto.getApplicationId());

		if (loanApplication == null) {
			throw new LoanApplicationNotFoundException(
					"LOAN APPLICATION ID " + loanApplicationDto.getApplicationId() + "IS NOT FOUND.");
		}
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.findById(loanApplicationDto.getCustomerid());
		
		if (customer == null) {
			throw new CustomerNotFoundException("CUSTOMER ID IS NOT FOUND.");
		}

		loanApplication.setCustomer(customer);
		loanApplication.setLoanType(loanApplicationDto.getLoanType());
		loanApplication.setPurpose(loanApplicationDto.getPurpose());
		loanApplication.setRequestedAmount(loanApplicationDto.getRequestedAmount());
		loanApplication.setStatus(loanApplicationDto.getStatus());

		loanApplicationDAO.update(loanApplication);

	}

	// DELETE A LOAN APPLICATION

	public void deleteLoanApplication(LoanApplicationDto loanApplicationDto) {

		if (loanApplicationDto == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION IS NOT FOUND");
		}

		LoanApplication deletingLoanApplication = loanApplicationDAO.findById(loanApplicationDto.getApplicationId());

		if (deletingLoanApplication == null) {
			throw new LoanApplicationNotFoundException(
					"LOAN APPLICATION ID " + loanApplicationDto.getApplicationId() + " IS NOT FOUND.");
		}

		loanApplicationDAO.delete(deletingLoanApplication);
	}

	// SEARCH A LOAN APPLICATION

	public LoanApplicationDto searchLoanApplication(Long loanApplicationId) {

		LoanApplication loanApplication = loanApplicationDAO.findById(loanApplicationId);

		if (loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION " + loanApplicationId + " IS  NOT FOUND");
		}
		
		

		LoanApplicationDto loanApplicationDto = new LoanApplicationDto();

		loanApplicationDto.setApplicationId(loanApplication.getApplicationId());
		loanApplicationDto.setCustomerid(loanApplication.getCustomer().getCustomerId());
		loanApplicationDto.setAppDate(loanApplication.getAppDate());
		loanApplicationDto.setLoanType(loanApplication.getLoanType());
		loanApplicationDto.setPurpose(loanApplication.getPurpose());
		loanApplicationDto.setRequestedAmount(loanApplication.getRequestedAmount());
		loanApplicationDto.setStatus(loanApplication.getStatus());

		return loanApplicationDto;
	}

	// GET ALL LOAN APPLICATIONS

	public List<LoanApplicationDto> getAllLoanApplications() {

		List<LoanApplication> loanApplications = loanApplicationDAO.findAll();

		List<LoanApplicationDto> loanApplicationDtos = new ArrayList<>();

		for (LoanApplication l : loanApplications) {

			LoanApplicationDto loanApplicationDto = new LoanApplicationDto();

			loanApplicationDto.setApplicationId(l.getApplicationId());
			loanApplicationDto.setCustomerid(l.getCustomer().getCustomerId());
			loanApplicationDto.setLoanType(l.getLoanType());
			loanApplicationDto.setPurpose(l.getPurpose());
			loanApplicationDto.setRequestedAmount(l.getRequestedAmount());
			loanApplicationDto.setStatus(l.getStatus());
			loanApplicationDto.setAppDate(l.getAppDate());

			loanApplicationDtos.add(loanApplicationDto);
		}

		return loanApplicationDtos;

	}

}
