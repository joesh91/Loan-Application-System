package com.loan.dao;

import java.util.List;

import com.loan.entity.LoanApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoanApplicationDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(LoanApplication lapp) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(lapp);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(LoanApplication lapp) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(lapp));
		em.getTransaction().commit();
		em.close();
		
	}

	public void update(LoanApplication lapp) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(lapp);
		em.getTransaction().commit();
		em.close();

	}

	public LoanApplication findById(Long loanId) {

		EntityManager em = emf.createEntityManager();

		LoanApplication la = em.find(LoanApplication.class, loanId);
		em.close();

		return la;
	}

	public List<LoanApplication> findAll() {

		EntityManager em = emf.createEntityManager();

		List<LoanApplication> la = em.createQuery("SELECT L FROM LoanApplication L", LoanApplication.class)
				.getResultList();

		em.close();
		return la;
	}
	
	public List<LoanApplication> findByCustomerId(Long custoemrId){
		
		EntityManager em = emf.createEntityManager();		
		
		List<LoanApplication> loanApplications = em.createQuery("SELECT L FROM LoanApplication L WHERE L.customer.customerId =:customerId",
				LoanApplication.class)
				.setParameter("customerId",custoemrId)
				.getResultList();
		
		em.close();
		
		return loanApplications;
		
	}
	
	public LoanApplication findByIdandCustomerId(Long id,Long customerId) {
		
		EntityManager em = emf.createEntityManager();
		
		LoanApplication loanApplication = em.createQuery("SELECT L FROM LoanApplication L WHERE L.applicationId =:applicationId AND L.customer.customerId =:customerId",LoanApplication.class)
				.setParameter("applicationId", id)
				.setParameter("customerId", customerId)
				.getResultStream()
				.findFirst()
				.orElse(null);
				
		em.close();
		return loanApplication;
	}
	
	/*public void updateLoanApplicationStatus(Long applicationId , LoanApplicationStatus status) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			
		}catch(Exception e) {
			
		}finally {
			
		}*/
	}
	
	