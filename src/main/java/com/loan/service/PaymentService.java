package com.loan.service;

import java.util.List;

import com.loan.dao.PaymentDAO;
import com.loan.entity.Payment;

public class PaymentService {

	PaymentDAO paymentDAO = new PaymentDAO();

	// SUBMIT A PAYMENT

	public void makePayment(Payment payment) {

		paymentDAO.save(payment);

	}

	// UPDATE A PAYMENT

	public void updatePayment(Payment payment) {

		paymentDAO.update(payment);
	}

	// DELETE A PAYMENT

	public void deletePayment(Payment payment) {

		paymentDAO.delete(payment);
	}

	// SEARCH A PAYMENT

	public Payment findPayment(int paymentID) {

		return paymentDAO.findById(paymentID);
	}

	// GET ALL PAYMENTS

	public List<Payment> getAllPayments() {

		return paymentDAO.findAll();
	}

	// UPDATE PAYMENT STATUS

	public void makeDecision(int paymentID, String paymentStatus) {

		Payment payment = paymentDAO.findById(paymentID);

		if (payment != null) {
			payment.setPaymentStatus(paymentStatus);

			paymentDAO.update(payment);
		} else {
			System.out.println(" **EMPTY PAYMENT OBJECT**");
		}

	}

}
