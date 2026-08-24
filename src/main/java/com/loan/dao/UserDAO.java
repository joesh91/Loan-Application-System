package com.loan.dao;

import com.loan.entity.User;
import java.util.List;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;

public class UserDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void save(User user) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void update(User user) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(User user) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(user));
		em.getTransaction().commit();
		em.close();
	}

	public User findById(Long userId) {

		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, userId);
		em.close();

		return user;
	}

	public List<User> findAll() {

		EntityManager em = emf.createEntityManager();

		List<User> users = em.createQuery("SELECT U FROM User U", User.class).getResultList();
		em.close();

		return users;

	}
	
	public User findByUserName(String userName) {
		
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT U FROM User U WHERE U.userName = :userName",User.class)
					.setParameter("userName", userName)
					.getResultStream()
					.findFirst()
					.orElse(null);
			
		}finally{
			em.close();			
		}
	}
}
