package com.loan.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.constraints.NotBlank;



public class LoanDocumentDto {
	
	private Long documentId;

	@NotBlank(message="DOCUMENT TYPE CANNOT BE NULL")
	private String documentType;
	
	@NotBlank(message="FILE NAME CANNOT BE NULL")
	private String fileName;
	
	private byte[] fileData;


	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDate uploadedAt;
	
	@NotBlank(message="STATUS CANNOT BE NULL")
	private String status;
	
	
	private Long loanApplicationId;


	public LoanDocumentDto() {

	}

	public Long getDocumentId() {
		return documentId;
	}


	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}


	public String getDocumentType() {
		return documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public LocalDate getUploadedAt() {
		return uploadedAt;
	}


	public void setUploadedAt(LocalDate uploadedAt) {
		this.uploadedAt = uploadedAt;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getLoanApplicationId() {
		return loanApplicationId;
	}


	public void setLoanApplicationId(Long loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}
	
	
	
	
}
