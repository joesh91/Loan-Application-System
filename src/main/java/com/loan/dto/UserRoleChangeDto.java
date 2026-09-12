package com.loan.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRoleChangeDto {

	@NotBlank(message="Role Should not be Empty")
	private String role;


	//	CONSTRUCTOR

	public UserRoleChangeDto() {

	}

	//	GETTERS AND SETTERS


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}





}
