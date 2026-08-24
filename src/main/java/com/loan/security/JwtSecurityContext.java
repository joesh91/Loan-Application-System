package com.loan.security;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;

public class JwtSecurityContext implements SecurityContext{
	
	private String userName;
	private String role;
	
	public JwtSecurityContext(String userName, String role) {
		this.userName = userName;
		this.role = role;
	}

	@Override
	public Principal getUserPrincipal() {
		
		return ()-> userName;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return this.role.equals(role);
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return "Bearer";
	}

}
