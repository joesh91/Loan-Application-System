package com.loan.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
	

	private Long userId;

	@NotBlank(message="USER NAME IS REQUIRED.")
	private String userName;

	@NotBlank(message="PASSWORD IS REQUIRED.")
	private String passWord;

	@NotBlank(message="ROLE IS REQUIRED.")
	private String role;

	@NotNull(message="CUSTOMER ID IS REQUIRED.")
	private Long customerId;

	
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}
