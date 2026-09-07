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
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserName(userName);
		
		OTP otp = otpDAO.findOtpByUser(user);
		
		if(otp == null) {
			return false;
		}
		
		if(otp.isUsed() == true) {
			return false;
		}
		
		if(otp.getExpiredAt().isBefore(LocalDateTime.now())) {
			return false;
		}
		
		if(!otp.getOtpCode().equals(enteredOtp)) {
			return false;
		}
		
		otp.setUsed(true);
		otpDAO.updateOtp(otp);
		
		return true;
	}
	
}
