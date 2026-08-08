package com.loan.test;

import com.loan.service.LoanApplicationService;
import com.loan.entity.Customer;
import com.loan.service.CustomerService;
import com.loan.entity.LoanApplication;

public class LoanApplicationServiceTest {
	public static void main(String[] args) {

		LoanApplicationService loanApplicationService = new LoanApplicationService();

		// GETTING CUSTOMER OBJECT DETAILS

		CustomerService customerService = new CustomerService();
		Customer customer = customerService.findCustomer(2);

		// GETTING LOAN APPLICATION OBJECT DETAILS

		LoanApplication loanApplication = loanApplicationService.searchLoanApplication(1);

		System.out.println(loanApplicationService.getAllLoanApplications());

		// UPDATE LOAN DETAILS

		loanApplication.setPurpose("TEST UPDATED");
		loanApplicationService.updateLoanApplication(loanApplication);

	}

}
