package com.loan.dao;

import java.util.List;

import com.loan.entity.ApplicationReview;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ApplicationReviewDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(ApplicationReview appReview) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(appReview);
		em.getTransaction().commit();
		em.close();
	}

	public void update(ApplicationReview appReview) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(appReview);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(ApplicationReview appReview) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(appReview);
		em.getTransaction().commit();
		em.close();
	}

	public ApplicationReview findById(int appReview) {

		EntityManager em = emf.createEntityManager();

		ApplicationReview app = em.find(ApplicationReview.class, appReview);
		em.close();

		return app;
	}

	public List<ApplicationReview> findAll() {

		EntityManager em = emf.createEntityManager();

		List<ApplicationReview> apps = em.createQuery("SELECT A FROM ApplicationReview A", ApplicationReview.class)
				.getResultList();
		em.close();

		return apps;
	}

}