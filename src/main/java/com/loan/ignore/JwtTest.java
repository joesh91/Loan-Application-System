package com.loan.ignore;

import com.loan.dao.CustomerDAO;
import com.loan.entity.Customer;
import com.loan.entity.User;
import com.loan.security.JwtUtil;
import com.loan.service.EmailService;
import com.loan.service.UserService;

import io.jsonwebtoken.Claims;

public class JwtTest {
	public static void main(String [] args) {
		
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.findById(35l);
		
		User user = new User();
		user.setUserName("TEST USER");
		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken(user);
		System.out.println(token);
		
		
		// TOKEN VALIDATION TEST
		
		Claims claims = jwtUtil.validateToken(token);
		
		System.out.println("USER NAME : " + claims.getSubject());
		System.out.println("USER NAME : " + claims.getIssuedAt());
		System.out.println("USER NAME : " + claims.getExpiration());
		
		//	NEW USER CREATION TESTING 
		
		UserService u = new UserService();
	
		
		
		EmailService email = new EmailService();
		email.sendCustomerCredentials("sha.eranga@gmail.com", "test1", "test1");
		
	}
}