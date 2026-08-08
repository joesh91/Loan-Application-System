package com.loan.service;

import com.loan.dao.LoanApplicationDAO;
import com.loan.entity.LoanApplication;
import java.util.List;

public class LoanApplicationService {

	LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();

	// SUBMIT A LOAN APPLICATION

	public void submitLoanApplication(LoanApplication loanApplication) {

		loanApplicationDAO.save(loanApplication);

	}

	// UPDATE A LOAN APPLICATION

	public void updateLoanApplication(LoanApplication loanApplication) {

		loanApplicationDAO.update(loanApplication);
	}

	// DELETE A LOAN APPLICATION

	public void deleteLoanApplication(LoanApplication loanApplication) {

		loanApplicationDAO.delete(loanApplication);
	}

	// SEARCH A LOAN APPLICATION

	public LoanApplication searchLoanApplication(int loanId) {

		return loanApplicationDAO.findById(loanId);
	}

	// GET ALL LOAN APPLICATIONS

	public List<LoanApplication> getAllLoanApplications() {

		return loanApplicationDAO.findAll();
	}
}
