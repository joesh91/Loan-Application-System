package com.loan.service;

import com.loan.entity.Loan;
import com.loan.entity.LoanApplication;
import com.loan.exception.LoanApplicationNotFoundException;
import com.loan.exception.LoanNotFoundException;
import com.loan.dao.LoanApplicationDAO;
import com.loan.dao.LoanDAO;
import com.loan.dto.LoanDecisionDto;
import com.loan.dto.LoanDto;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

	LoanDAO loanDAO = new LoanDAO();

	// SUBMIT A LOAN

	public void submitLoan(LoanDto loanDto) {
		
		if(loanDto == null) {
			throw new LoanNotFoundException("LOAN DEATILS CANNOT BE EMPTY. ");
		}
		
		LoanApplicationDAO loanApplicationDao = new LoanApplicationDAO();
		LoanApplication loanApplication = loanApplicationDao.findById(loanDto.getApplicationId());
			
		if(loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION ID " +loanDto.getApplicationId()+ " IS NOT FOUND.");
		}
		
		Loan loan = new Loan();
		
		loan.setApplication(loanApplication);
		loan.setAppAmount(loanDto.getAppAmount());
		loan.setDuration(loanDto.getDuration());
		loan.setEndDate(loanDto.getEndDate());
		loan.setIntRate(loanDto.getIntRate());
		loan.setStatus(loanDto.getStatus());
	
		loanDAO.save(loan);

	}

	// UPDATE A LOAN

	public void updateLoan(LoanDto loanDto) {

		Loan loan = loanDAO.findById(loanDto.getLoanId());
		
		if(loan == null) {
			throw new LoanNotFoundException("LOAN DETAILS NOT FOUND.");
		}
		
		LoanApplicationDAO loanApplicationDao = new LoanApplicationDAO();
		LoanApplication loanApplication = loanApplicationDao.findById(loanDto.getApplicationId());
		
		if(loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION IS EMPTY.");
		}
		
		loan.setApplication(loanApplication);
		loan.setAppAmount(loanDto.getAppAmount());
		loan.setDuration(loanDto.getDuration());
		loan.setEndDate(loanDto.getEndDate());
		loan.setIntRate(loanDto.getIntRate());
		loan.setStatus(loanDto.getStatus());
		
		loanDAO.update(loan);

	}

	// DELETE A LOAN

	public void deleteLoan(LoanDto loanDto) {
		
		if(loanDto == null) {
			throw new LoanNotFoundException("LOAN DETAILS CANNOT BE EMPTY.");
		}

		Loan loan = loanDAO.findById(loanDto.getLoanId());
		
		if(loan == null) {
			throw new LoanNotFoundException("LOAN DETAILS NOT FOUND.");
		}
		
		loanDAO.delete(loan);		

	}

	// FIND A LOAN

	public LoanDto findLoan(Long loanId) {
		
		Loan loan = loanDAO.findById(loanId);
		
		if(loan == null) {
			throw new LoanNotFoundException("LOAN DETAILS CANNOT BE EMPTY.");
		}
		
		LoanDto loanDto = new LoanDto();
		
		loanDto.setLoanId(loan.getLoanId());
		loanDto.setApplicationId(loan.getApplication().getApplicationId());
		loanDto.setAppAmount(loan.getAppAmount());
		loanDto.setDuration(loan.getDuration());
		loanDto.setEndDate(loan.getEndDate());
		loanDto.setIntRate(loan.getIntRate());
		loanDto.setStartDate(loan.getStartDate());
		loanDto.setStatus(loan.getStatus());
		
		return loanDto;

	}

	// GET ALL LOANS

	public List<LoanDto> getAllLoans() {

		List<LoanDto> loanDtos = new ArrayList<>();
		
		List<Loan> loans = loanDAO.findAll();
		
		for(Loan l : loans ) {
			
			LoanDto loanDto = new LoanDto();
			
			loanDto.setApplicationId(l.getApplication().getApplicationId());
			loanDto.setLoanId(l.getLoanId());
			loanDto.setAppAmount(l.getAppAmount());
			loanDto.setDuration(l.getDuration());
			loanDto.setEndDate(l.getEndDate());
			loanDto.setStartDate(l.getStartDate());
			loanDto.setIntRate(l.getIntRate());
			loanDto.setStatus(l.getStatus());
		
			loanDtos.add(loanDto);
		}
		
		return loanDtos;
		
	}

	// MAKE A DECISION

	public void makeDecision(LoanDecisionDto loanDecisionDto) {

		if(loanDecisionDto == null) {
			throw new LoanNotFoundException("LOAN DETAILS NOT FOUND");
		}
		
		Loan loan = loanDAO.findById(loanDecisionDto.getLoanId());

		loan.setStatus(loanDecisionDto.getStatus());
		
		loanDAO.update(loan);
	}

}
