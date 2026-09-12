package com.loan.dto;

import com.loan.enums.RegistrationStatus;

import jakarta.validation.constraints.NotNull;

public class CustomerRegistrationApproveDto {

	//	ATTRIBUTES

	private Long CustomerRegistrationId; // VIP

	@NotNull(message="STATUS CANNOT BE EMPTY")
	private RegistrationStatus status;


	//	CONSTRUCTION

	public CustomerRegistrationApproveDto() {

	}

	//	GETTERS AND SETTERS

	public Long getCustomerRegistrationId() {
		return CustomerRegistrationId;
	}


	public void setCustomerRegistrationId(Long customerRegistrationId) {
		CustomerRegistrationId = customerRegistrationId;
	}


	public RegistrationStatus getStatus() {
		return status;
	}


	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}





}
