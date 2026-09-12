package com.loan.service;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

	String email;
	String username;
	String temporaryPassword;

	String otp;

	public void sendCustomerCredentials(String email,String userName,String temporaryPassword) {

	//	SENDER EMAIL

		final String senderEmail = "sas.eranga@gmail.com";

	//	APP PASSWORD

		final String senderPassword = "pvpv hilm mssb dqzh";

	//	SMTP CONFIGURATION

		Properties properties = new Properties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");


	//	CREATE MAIL SESSION

		Session session = Session.getInstance(
					properties,new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(senderEmail,senderPassword);
							}
						}
					);
		try {

			//	CREATE EMAIL

			Message message = new MimeMessage(session);

			//	FROM

			message.setFrom(new InternetAddress(senderEmail));

			//	TO

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			//	SUBJECT

			message.setSubject("YOUR LOAN APPLICATION ACCOUNT");

			//	EMAIL BODY

			message.setText(

					"Dear Customer,\n\n"
					+"Your customer account has been created successfully.\n\n"
					+"Username : "+userName+ "\n\n"
					+"Temporary Password : "+temporaryPassword+"\n\n"
					+"Please use these credentials to login. \n\n"
					+"You will be required to change the password after the first log in. \n\n"
					+"Thank You. "
				);

			//	SENDER EMAIL

			Transport.send(message);

			// FOR THE CONSOLE

			System.out.println("EMAIL SENT SUCCESSFULLY.");

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void sendOtpEmail(String email,String otp) {

		String senderEmail = "sas.eranga@gmail.com";
		String senderEmailPassword = "pvpv hilm mssb dqzh";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");

		// CREATE MAIL SESSION

		Session session = Session.getInstance(
				properties,new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmail,senderEmailPassword);
						}
					}
				);

		try {
		//	CREATE MIME MESSAGE

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(senderEmail));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("OTP . PLEASE DO NOT SHARE.");
		message.setText(
				"Dear Customer,\n\n"
						+"USE THIS ONE TIME PASSWORD FOR CONFIRMATION\n\n"
						+"\n\n"
						+otp +"\n\n"
						+"\n\n"
						+"Thank You."

				);

		Transport.send(message);
		System.out.println("EMAIL SENT SUCCESSFULLY.");

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
