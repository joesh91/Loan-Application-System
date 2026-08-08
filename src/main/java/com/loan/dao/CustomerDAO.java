package com.loan.dao;

import java.util.List;

import com.loan.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CustomerDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(Customer customer) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();

	}

	public void update(Customer customer) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(Customer customer) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(customer));
		em.getTransaction().commit();
		em.close();

	}

	public Customer findById(int customerID) {

		EntityManager em = emf.createEntityManager();
		Customer customer = em.find(Customer.class, customerID);
		em.close();

		return customer;
	}

	public List<Customer> findAll() {

		EntityManager em = emf.createEntityManager();

		List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
		System.out.println("");

		return customers;
	}

}
