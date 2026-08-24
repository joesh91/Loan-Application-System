package com.loan.ignore;

import com.loan.entity.User;
import com.loan.security.JwtUtil;

import io.jsonwebtoken.Claims;

public class JwtTest {
	public static void main(String [] args) {
		
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
		
		
		
		
	}
}