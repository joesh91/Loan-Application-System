package com.loan.dto;

import java.time.LocalDateTime;

import com.loan.enums.RegistrationStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class CustomerRegistrationDto {


	private Long CustomerRegistrationId; // VIP

	@NotBlank(message="FIRST NAME CANNOT BE EMPTY")
	private String firstName;

	@NotBlank(message="LAST NAME CANNOT BE EMPTY")
	private String lastName;

	@NotBlank(message="NIC CANNOT BE EMPTY")
	private String nic;

	@NotBlank(message="EMAIL CANNOT BE EMPTY")
	@Email(message = "INVALID EMAIL FORMAT")
	private String email;

	@NotBlank(message="PHONE NUMBER CANNOT BE EMPTY")
	private String phone;

	@NotBlank(message="ADDRESS CANNOT BE EMPTY")
	private String address;

	private RegistrationStatus status;

	private LocalDateTime createdAt;	// VIP

	//	INITIALIZE EMPTY CONSTRUCTOR

	public CustomerRegistrationDto() {}

	//	DECLARE GETTERS AND SETTERS

	public Long getCustomerRegistrationId() {
		return CustomerRegistrationId;
	}

	public void setCustomerRegistrationId(Long customerRegistrationId) {
		CustomerRegistrationId = customerRegistrationId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}



}
