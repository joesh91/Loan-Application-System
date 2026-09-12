package com.loan.dao;

import java.util.List;

import com.loan.entity.CustomerRegistration;
import com.loan.enums.RegistrationStatus;

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


	// FIND A CUSTOMER REGISTRATION BY NIC

	public CustomerRegistration findByNic(String nic) {

		EntityManager em = emf.createEntityManager();

		CustomerRegistration customerRegistration = em.createQuery(
				"SELECT C FROM CustomerRegistration C WHERE C.nic = :nic",CustomerRegistration.class)
				.setParameter("nic", nic)
				.getResultStream()
				.findFirst()
				.orElse(null);
		em.close();
		return customerRegistration;
	}


	//	FIND ACTIVE REGISTRATION BY NIC

	public CustomerRegistration findActiveRegistrationByNic(String nic) {

		EntityManager em = emf.createEntityManager();

		CustomerRegistration customerRegistration = em.createQuery(
				"SELECT C FROM CustomerRegistration C WHERE "+
				"C.nic = :nic "+
				"AND C.status IN :statuses",
				CustomerRegistration.class)
				.setParameter("nic", nic)
				.setParameter("statuses",
				 List.of(RegistrationStatus.PENDING , RegistrationStatus.APPROVED)) // 		PROVIDE THE ACTIVE REGISTRATION STATUSES (PENDING OR APPROVED)
				.getResultStream()													//		query is executed and the matching results are provided as a Stream.
				.findFirst()														//		Give me the first matching registration
				.orElse(null);														//		if there is no matching registration return null
		em.close();

		return customerRegistration;
	}













}
