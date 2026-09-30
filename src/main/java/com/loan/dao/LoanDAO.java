package com.loan.dao;

import java.util.List;

import com.loan.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoanDAO {
	// CREATE ENTITY MANAGER FACTORY

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(Loan loan) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(loan);
		em.getTransaction().commit();
		em.close();

	}

	public void update(Loan loan) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(loan);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(Loan loan) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(loan));
		em.getTransaction().commit();
		em.close();
	}

	public Loan findById(Long loanId) {

		EntityManager em = emf.createEntityManager();

		Loan ln = em.find(Loan.class, loanId);

		em.getTransaction().begin();

		em.close();

		return ln;
	}

	public List<Loan> findAll() {

		EntityManager em = emf.createEntityManager();

		List<Loan> loans = em.createQuery("SELECT L FROM Loan L", Loan.class).getResultList();

		em.close();

		return loans;
	}
	
	
	public Loan findByIdandCustoemrId(Long id,Long customerId) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.createQuery("SELECT L FROM Loan L WHERE L.loanId =:LoanId AND L.application.customer.customerId =:customerId",Loan.class)
					.setParameter("LoanId",id)
					.setParameter("customerId",customerId)
					.getResultStream()
					.findFirst()
					.orElse(null);
		}
		finally {
			em.close();
		}
	}
	

	public List<Loan> findLoansByCustomerId(Long customerId) {

	    EntityManager em = emf.createEntityManager();

	    try {

	        return em.createQuery(
	                "SELECT L FROM Loan L " +
	                "WHERE L.application.customer.customerId = :customerId",
	                Loan.class)
	                .setParameter("customerId", customerId)
	                .getResultList();

	    } finally {

	        em.close();
	    }
	}


	


}
