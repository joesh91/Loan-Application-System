package com.loan.test;

import com.loan.service.LoanService;
import com.loan.entity.Loan;
import com.loan.entity.LoanApplication;
import com.loan.service.LoanApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LoanServiceTest {
	public static void main(String[] args) {

		LoanService loanService = new LoanService();

		LoanApplicationService loanApplicationService = new LoanApplicationService();
		LoanApplication loanApplication = new LoanApplication();

		loanApplication = loanApplicationService.searchLoanApplication(1);

		Loan loan = loanService.findLoan(1);

		/*
		 * System.out.println(loan);
		 * 
		 * /* Loan loan1 = new Loan(); loan1.setAppAmount(new BigDecimal(100000));
		 * loan1.setApplicationId(loanApplication); loan1.setDurationM(13);
		 * loan1.setEndDate(LocalDate.now()); loan1.setIntRate(20);
		 * loan1.setStartDate(LocalDate.now()); loan1.setStatus("PENDING TEST");
		 * 
		 * 
		 * loanService.submitLoan(loan1);
		 */

		System.out.println(loanService.getAllLoans() + "****");
	}

}
