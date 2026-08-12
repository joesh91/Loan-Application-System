package com.loan.service;

import java.util.ArrayList;
import java.util.List;

import com.loan.dao.LoanDAO;
import com.loan.dao.PaymentDAO;
import com.loan.dto.PaymentDto;
import com.loan.entity.Loan;
import com.loan.entity.Payment;
import com.loan.exception.LoanNotFoundException;
import com.loan.exception.PaymentNotFoundException;

public class PaymentService {

	PaymentDAO paymentDAO = new PaymentDAO();

	// SUBMIT A PAYMENT

	public void makePayment(PaymentDto paymentDto) {

		if(paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}
		
		LoanDAO loanDao = new LoanDAO();
		Loan loan = loanDao.findById(paymentDto.getLoanId());
		
		if(loan == null) {
			throw new LoanNotFoundException("LOAN ID " + paymentDto.getLoanId() + " NOT FOUND.");
		}	
		
		Payment payment = new Payment();
		
		payment.setLoan(loan);
		payment.setAmount(paymentDto.getAmount());
		payment.setPaymentStatus(paymentDto.getPaymentStatus());
		
		paymentDAO.save(payment);	

	}

	// UPDATE A PAYMENT

	public void updatePayment(PaymentDto paymentDto) {

		if(paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}
		
		Payment payment = paymentDAO.findById(paymentDto.getPaymentId());
		
		if(payment == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS ARENOT FOUND");
		}
		
		LoanDAO loanDao = new LoanDAO();
		Loan loan = loanDao.findById(paymentDto.getLoanId());
		
		
		payment.setAmount(paymentDto.getAmount());
		payment.setLoan(loan);
		payment.setPaymentDate(paymentDto.getPaymentDate());
		payment.setPaymentStatus(paymentDto.getPaymentStatus());
	
		
		paymentDAO.update(payment);
	}

	// DELETE A PAYMENT

	public void deletePayment(PaymentDto paymentDto) {

		if(paymentDto == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS CANNOT BE EMPTY.");
		}
		
		Payment delitingPayment  = paymentDAO.findById(paymentDto.getPaymentId());
		
		if(delitingPayment == null) {
			throw new PaymentNotFoundException("PAYMENT EDTAILS ARE NOT FOUND");
		}
		
		paymentDAO.delete(delitingPayment);
		
	}

	// SEARCH A PAYMENT

	public PaymentDto findPayment(Long paymentID) {

		Payment payment = paymentDAO.findById(paymentID);
			
			if(payment == null) {
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

		List <Payment> payments = paymentDAO.findAll();
		
		List <PaymentDto> paymentDtos = new ArrayList<>();
		
		for(Payment p : payments) {
			
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

	// UPDATE PAYMENT STATUS

	public void makeDecision(Long paymentID, String paymentStatus) {

		Payment payment = paymentDAO.findById(paymentID);

		if(payment == null) {
			throw new PaymentNotFoundException("PAYMENT DETAILS ARE NOT FOUND.");
		}
		payment.setPaymentStatus(paymentStatus);
		paymentDAO.update(payment);
	}
}