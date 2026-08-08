package com.loan.dao;

import com.loan.entity.Staff;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StaffDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(Staff staff) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(staff);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Staff staff) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(staff);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(Staff staff) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(staff));
		em.getTransaction().commit();
		em.close();
	}

	public Staff findById(int staffId) {

		EntityManager em = emf.createEntityManager();

		Staff staff = em.find(Staff.class, staffId);
		em.close();

		return staff;
	}

	public List<Staff> findAll() {

		EntityManager em = emf.createEntityManager();

		List<Staff> staff = em.createQuery("SELECT S FROM Staff S", Staff.class).getResultList();
		em.close();

		return staff;
	}

}
