package com.loan.dao;

import java.util.List;

import com.loan.entity.LoanDocument;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoanDocumentDAO {
	
	LoanDocument loanDocument = new LoanDocument();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");
	
	public void persist(LoanDocument loanDocument) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(loanDocument);
			em.getTransaction().commit();
			
		}finally {
			em.close();
		}
	}
	
	public void update(LoanDocument loanDocument ) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(loanDocument);
			em.getTransaction().commit();
			
		}finally {
			em.close();
		}
		
	}
	
	public void delete(LoanDocument loanDocument) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.remove(em.merge(loanDocument));
			em.getTransaction().commit();
			
		}finally {
			em.close();
			
		}
		
	}
	
	public LoanDocument getById(Long loanDocumentId) {
	
		EntityManager em = emf.createEntityManager();
		
		try {
				
			LoanDocument loanDocument = em.find(LoanDocument.class, loanDocumentId);
			return loanDocument;
		}finally {
			em.close();
		}
		
	
	}
	
	
	public List<LoanDocument> getAll(){
		
		EntityManager em = emf.createEntityManager();
		
		try {
		
		List<LoanDocument> loanDocs = em.createQuery("SELECT L FROM LoanDocument L",LoanDocument.class)
				.getResultList();
			return loanDocs;
		}finally {
		em.close();
		}
		
	}
	
}










