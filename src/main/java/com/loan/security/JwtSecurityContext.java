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

	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public Principal getUserPrincipal() {

		return ()-> userName;
	}

	@Override
	public boolean isUserInRole(String role) {
		System.out.println("isUserInRole IN jwt security context class method is called : "+role);

		return this.getRole().equals(role);
	//	return this.role.equals(role);

	}

	@Override
	public boolean isSecure() {
		System.out.println("ROLE CHECK 2");
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return "Bearer";
	}

}
