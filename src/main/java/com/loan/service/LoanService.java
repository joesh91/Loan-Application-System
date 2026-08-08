package com.loan.service;

import com.loan.entity.Loan;
import com.loan.dao.LoanDAO;
import java.util.List;

public class LoanService {

	LoanDAO loanDAO = new LoanDAO();

	// SUBMIT A LOAN

	public void submitLoan(Loan loan) {

		loanDAO.save(loan);

	}

	// UPDATE A LOAN

	public void updateLoan(Loan loan) {

		loanDAO.update(loan);

	}

	// DELETE A LOAN

	public void deleteLoan(Loan loan) {

		loanDAO.delete(loan);

	}

	// FIND A LOAN

	public Loan findLoan(int loanId) {

		return loanDAO.findById(loanId);

	}

	// GET ALL LOANS

	public List<Loan> getAllLoans() {

		return loanDAO.findAll();

	}

	// MAKE A DECISION

	public void makeDecision(String decision, int loanId) {

		Loan loan = loanDAO.findById(loanId);

		loan.setStatus(decision);

		loanDAO.update(loan);

	}

}
