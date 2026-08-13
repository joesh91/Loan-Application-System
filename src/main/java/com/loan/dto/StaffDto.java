package com.loan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StaffDto {

	
	private Long staffId;

	@NotBlank(message="NAME IS REQUIRED")
	private String name;

	@NotBlank(message="EMAIL IS REQUIRED.")
	@Email(message="INVALID EMAIL FORMAT")
	private String email;

	@NotBlank(message="POSITION IS REQURIED.")
	private String position;
	
	
	

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
