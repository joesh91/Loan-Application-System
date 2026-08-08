package com.loan.service;

import com.loan.dao.UserDAO;
import com.loan.entity.User;
import java.util.List;

public class UserService {

	UserDAO userDAO = new UserDAO();

// 	REGISTER USER 

	public void registerUser(User user) {

		userDAO.save(user);
	}

// 	UPDATE USER 

	public void updateUser(User user) {

		userDAO.update(user);
	}

// 	DELETE USER 

	public void deleteUser(User user) {

		userDAO.delete(user);
	}

// 	FIND USER 

	public User findUser(int userID) {

		return userDAO.findById(userID);
	}

// 	GET ALL USERS 

	public List<User> getAllUsers() {

		return userDAO.findAll();
	}
}
