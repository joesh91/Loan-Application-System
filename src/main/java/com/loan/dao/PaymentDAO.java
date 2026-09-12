package com.loan.dao;

import java.util.List;

import com.loan.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PaymentDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(Payment payment) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(payment);
		em.getTransaction().commit();
		em.close();

	}

	public void update(Payment payment) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(payment);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(Payment payment) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(payment));
		em.getTransaction().commit();
		em.close();
	}

	public Payment findById(Long paymentId) {

		EntityManager em = emf.createEntityManager();
		Payment py = em.find(Payment.class, paymentId);
		em.close();

		return py;
	}

	public List<Payment> findAll() {

		EntityManager em = emf.createEntityManager();

		List<Payment> py = em.createQuery("SELECT P FROM Payment P", Payment.class).getResultList();
		em.close();

		return py;
	}

}
