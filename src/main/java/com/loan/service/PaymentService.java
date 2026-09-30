package com.loan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.loan.dao.CustomerDAO;
import com.loan.dao.LoanDAO;
import com.loan.dao.PaymentDAO;
import com.loan.dao.UserDAO;
import com.loan.dto.PaymentDto;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Payment;
import com.loan.entity.User;
import com.loan.enums.PaymentStatus;
import com.loan.exception.CustomerNotFoundException;
import com.loan.exception.LoanNotFoundException;
import com.loan.exception.PaymentNotFoundException;
import com.loan.exception.UserNotFoundException;

public class PaymentService {

	PaymentDAO paymentDAO = new PaymentDAO();

	// SUBMIT A PAYMENT

	public void makePayment(PaymentDto paymentDto,String userName) {
		

		if (paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}

			//	USER 
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserName(userName);
		
		if(user == null) {
			throw new UserNotFoundException("User not found.");
		}
		
		//	CUSTOMER
		
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.findById(user.getCustomer().getCustomerId());
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer not found.");
		}
		
		
		//	LAON
		
		LoanService loanService = new LoanService();
		Loan loan = loanService.findLoanByIdandCustomerId(paymentDto.getLoanId(),customer.getCustomerId());	// loan id , CUSTOMERID

		if (loan == null) {
			throw new LoanNotFoundException("LOAN ID " + paymentDto.getLoanId() + " NOT FOUND.");
		}
		
		//	CREATE PAYMENT

		Payment payment = new Payment();

		payment.setLoan(loan);
		payment.setAmount(paymentDto.getAmount());
		payment.setPaymentStatus(PaymentStatus.PENDING);		
		paymentDAO.save(payment);

	}

	// UPDATE A PAYMENT

	public void updatePayment(PaymentDto paymentDto) {

		if (paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}

		Payment payment = paymentDAO.findById(paymentDto.getPaymentId());

		if (payment == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS ARENOT FOUND");
		}

		LoanDAO loanDao = new LoanDAO();
		Loan loan = loanDao.findById(paymentDto.getLoanId());

		payment.setAmount(paymentDto.getAmount());
		payment.setLoan(loan);
		payment.setPaymentStatus(paymentDto.getPaymentStatus());

		paymentDAO.update(payment);
	}

	// DELETE A PAYMENT

	public void deletePayment(PaymentDto paymentDto) {

		if (paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}

		Payment delitingPayment = paymentDAO.findById(paymentDto.getPaymentId());

		if (delitingPayment == null) {
			throw new PaymentNotFoundException("PAYMENT EDTAILS ARE NOT FOUND");
		}

		paymentDAO.delete(delitingPayment);

	}

	// SEARCH A PAYMENT

	public PaymentDto findPayment(Long paymentID) {

		Payment payment = paymentDAO.findById(paymentID);

		if (payment == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS NOT FOUND.");
		}

		PaymentDto paymentDto = new PaymentDto();

		paymentDto.setPaymentId(payment.getPaymentId());
		paymentDto.setLoanId(payment.getLoan().getLoanId());
		paymentDto.setAmount(payment.getAmount());
		paymentDto.setPaymentDate(payment.getPaymentDate());
		paymentDto.setPaymentStatus(payment.getPaymentStatus());

		return paymentDto;

	}

	// GET ALL PAYMENTS

	public List<PaymentDto> getAllPayments() {

		List<Payment> payments = paymentDAO.findAll();

		List<PaymentDto> paymentDtos = new ArrayList<>();

		for (Payment p : payments) {

			PaymentDto paymentDto = new PaymentDto();

			paymentDto.setAmount(p.getAmount());
			paymentDto.setLoanId(p.getLoan().getLoanId());
			paymentDto.setPaymentDate(p.getPaymentDate());
			paymentDto.setPaymentId(p.getPaymentId());
			paymentDto.setPaymentStatus(p.getPaymentStatus());

			paymentDtos.add(paymentDto);
		}

		return paymentDtos;
	}
/*
	// UPDATE PAYMENT STATUS			FOR LATER IMPLEMENTATION

	public void makeDecision(Long paymentID, String paymentStatus) {

		Payment payment = paymentDAO.findById(paymentID);

		if (payment == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS ARE NOT FOUND.");
		}
		payment.setPaymentStatus(paymentStatus);
		paymentDAO.update(payment);
	}
	*/
	
	public List<PaymentDto> getMyPayments(String username) {

	    UserDAO userDAO = new UserDAO();

	    User user = userDAO.findByUserName(username);

	    if (user == null) {
	        throw new UserNotFoundException(
	                "USER " + username + " NOT FOUND."
	        );
	    }

	    Customer customer = user.getCustomer();

	    if (customer == null) {
	        throw new CustomerNotFoundException(
	                "CUSTOMER NOT FOUND FOR USER " + username
	        );
	    }

	    List<Payment> payments =
	            paymentDAO.findPaymentsByCustomerId(
	                    customer.getCustomerId()
	            );

	    List<PaymentDto> paymentDtos = new ArrayList<>();

	    for (Payment payment : payments) {

	        PaymentDto dto = new PaymentDto();

	        dto.setPaymentId(payment.getPaymentId());
	        dto.setLoanId(payment.getLoan().getLoanId());
	        dto.setAmount(payment.getAmount());
	        dto.setPaymentDate(payment.getPaymentDate());
	        dto.setPaymentStatus(payment.getPaymentStatus());

	        paymentDtos.add(dto);
	    }

	    return paymentDtos;
	}
	
}