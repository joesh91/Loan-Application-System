package com.loan.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerDto {
	
	
	private Long customerID;
	
	@NotBlank(message="FIRST NAME IS REQUIRED")
	private String firstName;
	
	@NotBlank(message="LAST NAME IS REQUIRED")
	private String lastName;
	
	@NotBlank(message="NIC IS REQUIRED")
	private String nic;
	
	@NotBlank(message="EMAIL IS REQUIRED")
	@Email(message="INVALID EMAIL FORMAT")
	private String email;
	
	@NotBlank(message="PHONE NUMBER IS REQUIRED")
	private String phone;
	
	@NotBlank(message="ADDRESS IS REQUIRED")
	private String address;
	
	

	public Long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
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
	
	
	
	
	
	

}
