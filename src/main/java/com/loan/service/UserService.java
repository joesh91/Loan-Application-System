package com.loan.service;

import com.loan.dao.CustomerDAO;
import com.loan.dao.UserDAO;
import com.loan.dto.UserDto;
import com.loan.entity.Customer;
import com.loan.entity.User;
import com.loan.exception.CustomerNotFoundException;
import com.loan.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	UserDAO userDAO = new UserDAO();

// 	REGISTER USER 

	public void registerUser(UserDto userDto) {

		if(userDto == null) {
			throw new UserNotFoundException("USER DETAILS CANNOT BE EMPTY.");
		}
		
		CustomerDAO customerDao = new CustomerDAO();
		Customer customer = customerDao.findById(userDto.getCustomerId());
		
		User user = new User();
		
		user.setCustomer(customer);
		user.setPassWord(userDto.getPassWord());
		user.setRole(userDto.getRole());
		user.setUserName(userDto.getUserName());
		
		userDAO.save(user);
	}

// 	UPDATE USER 

	public void updateUser( UserDto userDto) {

		if(userDto==null) {
			throw new UserNotFoundException("USER DETAILS CANNOT BE EMPTY.");
		}
		
		User user = userDAO.findById(userDto.getUserId());
		
		if(user == null) {
			throw new UserNotFoundException("USER DETAILS ARE NOT FOUND.");
		}
		
		CustomerDAO customerDao = new CustomerDAO();
		Customer customer = customerDao.findById(userDto.getCustomerId());
		
		if(customer == null) {
			throw new CustomerNotFoundException("CUSTOMER DETAILS ARE NOT FOUND.");
		}
		
		user.setCustomer(customer);
		user.setPassWord(userDto.getPassWord());
		user.setRole(userDto.getRole());
		user.setUserName(userDto.getUserName());
		
		userDAO.update(user);
	}

// 	DELETE USER 

	public void deleteUser(UserDto userDto) {

		if(userDto == null) {
			throw new UserNotFoundException("USER DETAILS CANNOT BE EMPTY.");
		}
		
		User user = userDAO.findById(userDto.getUserId());
		
		if(user == null) {
			throw new UserNotFoundException("USER DETAILS ARE NOT FOUND.");
		}
		
		userDAO.delete(user);
	}

// 	FIND USER 

	public UserDto findUser(Long userID) {

		User user = userDAO.findById(userID);
		
		if(user == null) {
			throw new UserNotFoundException("USER DETAILS NOT FOUND.");
		}
		
		UserDto userDto = new UserDto();
		
		userDto.setCustomerId(user.getCustomer().getCustomerId());
		userDto.setPassWord(user.getPassWord());
		userDto.setRole(user.getPassWord());
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		
		return userDto;
	}

// 	GET ALL USERS 

	public List<UserDto> getAllUsers() {

		List<User> users = userDAO.findAll();
		
		List<UserDto> userDtos = new ArrayList<>();
		
		for(User u : users) {
			
			UserDto userDto = new UserDto();
			
			userDto.setCustomerId(u.getCustomer().getCustomerId());
			userDto.setPassWord(u.getPassWord());
			userDto.setRole(u.getRole());
			userDto.setUserId(u.getUserId());
			userDto.setUserName(u.getUserName());
			
			userDtos.add(userDto);
		}
		
		return userDtos;		
	}
}
