package com.loan.helper;

import java.util.UUID;

public class TemporaryPassword {

	private String temporaryPassword;

	public TemporaryPassword() {

	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}


	public String generateTemporaryPassword() {

		return "@Temp"+UUID.randomUUID().toString().substring(0,6);
	}

}
