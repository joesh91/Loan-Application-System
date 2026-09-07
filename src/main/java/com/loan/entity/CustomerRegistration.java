package com.loan.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.loan.enums.RegistrationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CUSTOMER_REGISTRATION")
public class CustomerRegistration {

	@Id
	@Column(name = "REGISTRATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CustomerRegistrationId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "NIC")
	private String nic;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CREATED_AT", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private RegistrationStatus status;
	
		// EMPTY CONSTRUCTOR
	
	public CustomerRegistration() {
		
	}
	
	// GETTERS AND SETTERS
	
	public Long getRegistrationId() {
		return CustomerRegistrationId;
	}

	public void setRegistrationId(Long registrationId) {
		this.CustomerRegistrationId = registrationId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}

	
	

}
