package com.loan.dto;

import com.loan.enums.RegistrationStatus;

import jakarta.validation.constraints.NotNull;

public class CustomerRegistrationApproveDto {
	
	
	private Long CustomerRegistrationId; // VIP
	
	@NotNull(message="STATUS CANNOT BE EMPTY")
	private RegistrationStatus status;
	
	
	public CustomerRegistrationApproveDto() {

	}


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
