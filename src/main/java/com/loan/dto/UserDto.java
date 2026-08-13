package com.loan.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class UserDto {
	

	private Long userId;

	@NotBlank(message="USER NAME IS REQUIRED.")
	@Size(max=10,message="USER NAME MUST NOT EXCEED 10 CHARACTERS.")
	private String userName;

	@NotBlank(message="PASSWORD IS REQUIRED.")
	@Size(max=25,message="PASSWORD MUST NOT EXCEED 25 CHARACTERS.")
	private String passWord;

	@NotBlank(message="ROLE IS REQUIRED.")
	@Size(max=100,message="ROLE CHARACTERS COUNT MUST NOT EXCEED 100.")
	private String role;

	@NotNull(message="CUSTOMER ID IS REQUIRED.")
	@Positive(message="CUSTOMER ID SHOULD NOT CONTAIN NEGATIVE VALUE")
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
