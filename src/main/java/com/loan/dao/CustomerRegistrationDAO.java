package com.loan.dao;

import com.loan.entity.CustomerRegistration;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CustomerRegistrationDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");
	
	//	SAVE NEW CUSTOMER REGISTRATION
	
	public void save (CustomerRegistration customerRegistration) {
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(customerRegistration);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		}finally {
			em.close();
		}
	}
	
	//	FIND A CUSTOMER REGISTRATION DETAILS BY ID
	
	public CustomerRegistration findById(Long customerRegistrationId) {
		
		EntityManager em = emf.createEntityManager();
		CustomerRegistration customerRegistration = em.find(CustomerRegistration.class, customerRegistrationId);
		em.close();
		return customerRegistration;
		
	}
	
	//	GET ALL THE CUSTOMER REGISTRATION DETAILS 
	
	public List<CustomerRegistration> findAll(){
		
		EntityManager em = emf.createEntityManager();
		
		List<CustomerRegistration> customerRegistrations= em.createQuery("SELECT C FROM CustomerRegistration C",CustomerRegistration.class).getResultList();
	
		em.close();
		
		return customerRegistrations;
	}
	
	//	UPDATE A CUSTOMER REGISTRAION DETAILS
	
	public void update(CustomerRegistration customerRegistration) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
		em.getTransaction().begin();
		em.merge(customerRegistration);
		em.getTransaction().commit();
		
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		}finally {
			em.close();
		}
	}
	
	//	DELETE A CUSTOMER REGISTRATION DETAILS
	
	public void delete(CustomerRegistration customerRegistration) {
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.remove(em.merge(customerRegistration));
			em.getTransaction().commit();
			
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		}finally{
			em.close();
		}
	}
	
	
}








