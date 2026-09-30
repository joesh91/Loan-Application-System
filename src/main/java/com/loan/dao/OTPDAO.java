package com.loan.dao;

import com.loan.entity.OTP;
import com.loan.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OTPDAO {

	OTP otp = new OTP();

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoanPu");

	public void saveOtp(OTP otp) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(otp);
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

	public void updateOtp(OTP otp) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(otp);
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


	public OTP getOtp(Long otpId ) {

		EntityManager em = emf.createEntityManager();

		try {
			OTP otp =  em.find(OTP.class, otpId);
			return otp;
		}finally {
			em.close();
		}

	}


	public OTP findOtpByUser(User user) {

		EntityManager em = emf.createEntityManager();

		try {
			OTP otp =	em.createQuery("SELECT C FROM OTP C WHERE C.userId=:user",OTP.class)
						.setParameter("user", user)
						.getResultStream()
						.findFirst()
						.orElse(null);

			return otp;
		}
		finally {
			em.close();
		}
	}

	public OTP getUnusedOtp(Long userId) {

		EntityManager em = emf.createEntityManager();

		try {
			OTP otp = em.createQuery("SELECT O FROM OTP O WHERE O.userId.userId=:USER_ID AND used =:STATUS ORDER BY O.otpId DESC",OTP.class)
					.setParameter("USER_ID", userId)
					.setParameter("STATUS", false)
					.getResultStream()
					.findFirst()
					.orElse(null);

			return otp;
		}finally{
			em.close();
		}
	}




	}

