package com.loan.dao;

import com.loan.entity.LoanApplication;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;

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
		System.out.println("PERSIST DONE");
	}

	public void update(LoanApplication lapp) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(lapp);
		em.getTransaction().commit();
		em.close();

	}

	public LoanApplication findById(int lapp) {

		EntityManager em = emf.createEntityManager();

		LoanApplication la = em.find(LoanApplication.class, lapp);
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
}
