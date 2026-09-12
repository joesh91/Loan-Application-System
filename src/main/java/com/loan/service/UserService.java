package com.loan.service;

import java.util.ArrayList;
import java.util.List;

import com.loan.dao.CustomerDAO;
import com.loan.dao.UserDAO;
import com.loan.dto.LoginRequestDto;
import com.loan.dto.UserDto;
import com.loan.dto.UserRoleChangeDto;
import com.loan.entity.Customer;
import com.loan.entity.User;
import com.loan.exception.AuthenticationException;
import com.loan.exception.CustomerNotFoundException;
import com.loan.exception.UserNotFoundException;
import com.loan.security.JwtUtil;

public class UserService {

		UserDAO userDAO = new UserDAO();

// 	REGISTER USER

	public void registerUser(UserDto userDto) {

		if (userDto == null) {
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

	public void updateUser(UserDto userDto) {

		if (userDto == null) {
			throw new UserNotFoundException("USER DETAILS CANNOT BE EMPTY.");
		}

		User user = userDAO.findById(userDto.getUserId());

		if (user == null) {
			throw new UserNotFoundException("USER DETAILS ARE NOT FOUND.");
		}

		CustomerDAO customerDao = new CustomerDAO();
		Customer customer = customerDao.findById(userDto.getCustomerId());

		if (customer == null) {
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

		if (userDto == null) {
			throw new UserNotFoundException("USER DETAILS CANNOT BE EMPTY.");
		}

		User user = userDAO.findById(userDto.getUserId());

		if (user == null) {
			throw new UserNotFoundException("USER DETAILS ARE NOT FOUND.");
		}

		userDAO.delete(user);
	}

// 	FIND USER

	public UserDto findUser(Long userID) {

		User user = userDAO.findById(userID);

		if (user == null) {
			throw new UserNotFoundException("USER DETAILS NOT FOUND.");
		}

		UserDto userDto = new UserDto();

		userDto.setCustomerId(user.getCustomer().getCustomerId());
		userDto.setPassWord(user.getPassWord());
		userDto.setRole(user.getRole());
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());

		return userDto;
	}

// 	GET ALL USERS

	public List<UserDto> getAllUsers() {

		List<User> users = userDAO.findAll();

		List<UserDto> userDtos = new ArrayList<>();

		for (User u : users) {

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

//	AUTHENTICATION

//	private JwtUtil jwtUtil = new JwtUtil();

	public void login(LoginRequestDto loginRequest) {

		User user = userDAO.findByUserName(loginRequest.getUserName());

			if(user == null) {
				throw new UserNotFoundException("INVALID USERNAME");
			}

			if(!user.getPassWord().equals(loginRequest.getPassWord())) {
				throw new AuthenticationException("INVALID PASSWORD");
			}
												//	IF USER NAME AND PASSWORD ARE CORRECT THEN OTP WILL BE CREATED AND SEND.
			OTPService otpService = new OTPService();
			otpService.createOtp(user);

	}


	public String verifyOtpAndGenerateToken(String username ,String enteredOtp) {
		OTPService otpService = new OTPService();

		boolean verified = otpService.verifyOtp(username, enteredOtp);

		if(!verified) {
			return null;
		}

		User user = userDAO.findByUserName(username);

		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken(user);


		return token;
	}

//	CHANGE USER ROLE

	public UserDto changeUserRole(Long userId,UserRoleChangeDto userRoleChangeDto) {
		System.out.println("USER SERVICE  CHANGE ROLE METHOD 1 ");
		User user = userDAO.findById(userId);

		if(user == null) {
			throw new UserNotFoundException("USER NOT FOUND");
		}

		System.out.println("USER SERVICE  CHANGE ROLE METHOD 2 ");
		user.setRole(userRoleChangeDto.getRole());

		userDAO.update(user);
		UserDto userDto = new UserDto();
		userDto.setCustomerId(user.getCustomer().getCustomerId());
		userDto.setUserName(user.getUserName());
		userDto.setRole(user.getRole());
		userDto.setUserId(user.getUserId());

		return userDto;
	}


//	CHANGE USER PASSWORD

	public void changePassword() {
		//	 TO DO CODE
	}



}
