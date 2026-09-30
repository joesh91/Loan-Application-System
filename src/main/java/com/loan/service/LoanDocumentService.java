package com.loan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.loan.dao.LoanApplicationDAO;
import com.loan.dao.LoanDocumentDAO;
import com.loan.dto.LoanDocumentDto;
import com.loan.entity.LoanApplication;
import com.loan.entity.LoanDocument;
import com.loan.exception.LoanApplicationNotFoundException;

public class LoanDocumentService {
	

	LoanDocumentDAO loanDocumentDAO = new LoanDocumentDAO();

	//	UPLOAD DOCUMENTS
	
	public void uploadDocument(	Long applicationId,
								String documentType,
								String fileName,
								byte[] fileData
								) {
		
		LoanDocument loanDocument = new LoanDocument();
		
		loanDocument.setDocumentType(documentType);
		loanDocument.setFileName(fileName);
		loanDocument.setFileData(fileData);
		loanDocument.setUploadedAt(LocalDate.now());
		loanDocument.setStatus("PENDING");
		
		
	   // set the LoanApplication here
		
		LoanApplicationDAO loappDAO = new LoanApplicationDAO();
		LoanApplication loanApplication = loappDAO.findById(applicationId);
		
		if(loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION NOT FOUND.");
		}
		
		loanDocument.setLoanApplication(loanApplication);
		
		
		loanDocumentDAO.persist(loanDocument);
	}
	
	//	UPDATE A LOAN DOCUMENT SET
	public void updateLoanDocument(LoanDocumentDto loanDocumentDto) {
		
		
		LoanDocument loanDocument = loanDocumentDAO.getById(loanDocumentDto.getDocumentId());
		LoanApplicationDAO loappDAO = new LoanApplicationDAO();
		LoanApplication loanApplication = loappDAO.findById(loanDocumentDto.getLoanApplicationId());
		
		if(loanApplication == null) {
			throw new LoanApplicationNotFoundException("LOAN APPLICATION NOT FOUND.");
		}
		
		loanDocument.setDocumentType(loanDocumentDto.getDocumentType());
		loanDocument.setFileName(loanDocumentDto.getFileName());
		loanDocument.setLoanApplication(loanApplication);
		loanDocument.setStatus(loanDocumentDto.getStatus());
		
		loanDocumentDAO.update(loanDocument);
	}

	//	DELETE LOAN DOCUMENT SET
	public void deleteLoanDocument(Long loanDocumentId) {
		
		LoanDocumentDAO loanDocumentDAO = new LoanDocumentDAO();
		LoanDocument loanDocument = loanDocumentDAO.getById(loanDocumentId);
		
		loanDocumentDAO.delete(loanDocument);
		
		
	}
	
	//	SEARCH LOAN DOCUMENT SET
	public LoanDocumentDto searchLoanDocumentDetails(Long loanDocumentId) {
		
		LoanDocumentDto loanDocumentDto = new LoanDocumentDto();
		
		LoanDocument loanDocument = loanDocumentDAO.getById(loanDocumentId);
		
		loanDocumentDto.setDocumentId(loanDocument.getDocumentId());
		loanDocumentDto.setDocumentType(loanDocument.getDocumentType());
		loanDocumentDto.setFileName(loanDocument.getFileName());
		loanDocumentDto.setLoanApplicationId(loanDocument.getLoanApplication().getApplicationId());
		loanDocumentDto.setStatus(loanDocument.getStatus());
		loanDocumentDto.setUploadedAt(loanDocument.getUploadedAt());
		
		return loanDocumentDto;
	}
	
	//	GET ALL LOAN DOCUMENTS
	public List<LoanDocumentDto> getAllLoanDocuments(){
		
		List<LoanDocumentDto> loanDocumentsDto = new ArrayList<>();
		
		List<LoanDocument> loanDocuments = loanDocumentDAO.getAll();
		
		for(LoanDocument l : loanDocuments) {
			LoanDocumentDto loanDocumentDto = new LoanDocumentDto();
			loanDocumentDto.setDocumentId(l.getDocumentId());
			loanDocumentDto.setDocumentType(l.getDocumentType());
			loanDocumentDto.setFileName(l.getFileName());
			loanDocumentDto.setLoanApplicationId(l.getLoanApplication().getApplicationId());
			loanDocumentDto.setStatus(l.getStatus());
			loanDocumentDto.setUploadedAt(l.getUploadedAt());
			
			
			loanDocumentsDto.add(loanDocumentDto);			
		}
		return loanDocumentsDto;	
	}
	
	//	 GET ACTUAL FILE DATA
	public LoanDocument getLoanDocument(Long loanDocumentId) {
		
		LoanDocument loanDocument = loanDocumentDAO.getById(loanDocumentId);
		
		if(loanDocument == null) {
			throw new RuntimeException("LOAN DOCUMENT NOT FOUND.");
		}
		
		return loanDocument;
	}
	
	
	//	GET DOCUMENTS BY LOAN APPLICATION ID
	
	public List<LoanDocumentDto> getDocumentsByLoanApplicationId(Long loanApplicationId) {
		
		List<LoanDocument> loanDocuments = loanDocumentDAO.getAllLoanDocuments(loanApplicationId);
		
		List<LoanDocumentDto> loanDocumentDto = new ArrayList<>();
		
		for(LoanDocument l : loanDocuments) {
			
			LoanDocumentDto loanDocumentdto = new LoanDocumentDto();
			
			loanDocumentdto.setDocumentId(l.getDocumentId());
			loanDocumentdto.setDocumentType(l.getDocumentType());
			loanDocumentdto.setFileName(l.getFileName());
			loanDocumentdto.setLoanApplicationId(l.getLoanApplication().getApplicationId());
			loanDocumentdto.setStatus(l.getStatus());
			loanDocumentdto.setUploadedAt(l.getUploadedAt());
			
			loanDocumentDto.add(loanDocumentdto);
		}
		
		
		return loanDocumentDto;
	}
	
	
	
	
	
	
	
}