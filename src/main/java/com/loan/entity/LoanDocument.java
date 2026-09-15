package com.loan.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name="LOAN_DOCUMENT")
public class LoanDocument {
	
	@Id
	@Column(name="document_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long documentId;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="uploaded_at")
	private LocalDate uploadedAt;
	
	@Column(name="STATUS")
	private String status;
	
	@JoinColumn(name="loanApplication")
	@ManyToOne
	private LoanApplication loanApplication;
	
	
	@JdbcTypeCode(SqlTypes.VARBINARY)
	@Column(name="file_data")
	private byte[] fileData;

	public LoanDocument() {
		
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

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	
	
	
	

}
