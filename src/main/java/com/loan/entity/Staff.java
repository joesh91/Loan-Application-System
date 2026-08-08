package com.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@Column(name = "staff_id")
	private int staffId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "position")
	private String position;

	// EMPTY CONSTRUCTOR

	public Staff() {

	}

	// GETTERS AND SETTERS

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
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

	// TO-STRING METHOD
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", name=" + name + ", email=" + email + ", position=" + position + "]";
	}

}