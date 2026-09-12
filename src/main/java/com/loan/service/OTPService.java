package com.loan.service;

import java.time.LocalDateTime;
import java.util.Random;

import com.loan.dao.OTPDAO;
import com.loan.dao.UserDAO;
import com.loan.entity.Customer;
import com.loan.entity.OTP;
import com.loan.entity.User;

public class OTPService {

	OTPDAO otpDAO = new OTPDAO();
	EmailService emailService = new EmailService();

		//	GENERATE NEW OTP

	public String generateOtp() {

	Random random = new Random();

	int number = random.nextInt(1000000);

	return String.format("%06d", number);

	}

		//	CREATE OTP INTO THE DATABASE & SENDING THME EMAIL CONTAINING OTP

	public void createOtp(User user) {

		OTP otp = new OTP();

		otp.setUserId(user);
		otp.setExpiredAt(LocalDateTime.now().plusWeeks(1));
		otp.setOtpCode(generateOtp());
		otp.setUsed(false);
		otpDAO.saveOtp(otp);


		Customer customer = user.getCustomer();
		String userEmail = customer.getEmail();


		emailService.sendOtpEmail(userEmail,otp.getOtpCode());			// sendOtpEmail(String email,String otp)
	}

	//	VERIFY OTP

	public boolean verifyOtp(String userName,String enteredOtp) {
		System.out.println("TEST OTP_SERVICE_1");

		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserName(userName);

		System.out.println("TEST OTP_SERVICE_2");


		OTP otp = otpDAO.getUnusedOtp(user.getUserId());

		System.out.println("TEST OTP_SERVICE_3");
		if(otp == null) {
			return false;
		}

		System.out.println("TEST OTP_SERVICE_4");
		if(otp.isUsed()) {
			return false;
		}

		System.out.println("TEST OTP_SERVICE_5");
		if(otp.getExpiredAt().isBefore(LocalDateTime.now())) {
			return false;
		}

		System.out.println("TEST OTP_SERVICE_6");
		if(!otp.getOtpCode().equals(enteredOtp)) {
			return false;
		}

		System.out.println("TEST OTP_SERVICE_7");
		otp.setUsed(true);
		otpDAO.updateOtp(otp);

		return true;
	}

}
