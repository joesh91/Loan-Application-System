package com.loan.entity;

import com.loan.enums.StaffPosition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@Column(name = "staff_id")
	private Long staffId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "position")
	@Enumerated(EnumType.STRING)
	private StaffPosition position;
	
	
	// EMPTY CONSTRUCTOR

	public Staff() {

	}

	// GETTERS AND SETTERS

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

	public StaffPosition getPosition() {
		return position;
	}

	public void setPosition(StaffPosition position) {
		this.position = position;
	}

	// TO-STRING METHOD
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", name=" + name + ", email=" + email + ", position=" + position + "]";
	}

}