package com.loan.test;

import java.time.LocalDate;
import com.loan.entity.Payment;
import com.loan.dao.PaymentDAO;
import com.loan.service.PaymentService;
import com.loan.entity.Loan;
import com.loan.service.LoanService;

public class PaymentServiceTest {
	public static void main(String[] args) {

		PaymentService paymentService = new PaymentService();

		// CREATE PAYMENT OBJECT AND SETTING ATTRIBUTES DETAILS
		/*
		 * Payment payment = new Payment(); payment.setAmount(100000);
		 * payment.setLoanId(null); payment.setPaymentDate(LocalDate.now());
		 * payment.setPaymentStatus("**PENDING**");
		 */
		// SAVING PAYMENT OBJECT INTO DATABASE THROUGH HIBERNATE
		/*
		 * paymentService.makePayment(payment);
		 * 
		 * 
		 * // UPDATING PAYMENT DETAILS
		 * 
		 * 
		 * Payment payment = new Payment(); payment.setAmount(200000);
		 * 
		 * 
		 * paymentService.updatePayment(payment);
		 */

		System.out.println(paymentService.getAllPayments());

	}
}