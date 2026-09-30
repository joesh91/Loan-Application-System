package com.loan.service;

import java.util.ArrayList;
import java.util.List;

import com.loan.dao.CustomerDAO;
import com.loan.dao.CustomerRegistrationDAO;
import com.loan.dao.UserDAO;
import com.loan.dto.CustomerRegistrationApproveDto;
import com.loan.dto.CustomerRegistrationDto;
import com.loan.entity.Customer;
import com.loan.entity.CustomerRegistration;
import com.loan.entity.User;
import com.loan.enums.RegistrationStatus;
import com.loan.helper.TemporaryPassword;

public class CustomerRegistrationService {

	CustomerRegistrationDAO customerRegistrationDAO = new CustomerRegistrationDAO();

	public void saveNewCustomerRegistration(CustomerRegistrationDto customerRegistrationDto) {

		CustomerRegistration existing = customerRegistrationDAO.findActiveRegistrationByNic(customerRegistrationDto.getNic());

		if(existing != null) {
			throw new IllegalArgumentException("DETAILS ALREADY EXIST FOR THIS NIC.");
		}

		CustomerRegistration customerRegistration = new CustomerRegistration();

		customerRegistration.setFirstName(customerRegistrationDto.getFirstName());
		customerRegistration.setLastName(customerRegistrationDto.getLastName());
		customerRegistration.setNic(customerRegistrationDto.getNic());
		customerRegistration.setPhone(customerRegistrationDto.getPhone());
		customerRegistration.setEmail(customerRegistrationDto.getEmail());
		customerRegistration.setAddress(customerRegistrationDto.getAddress());
		customerRegistration.setStatus(RegistrationStatus.PENDING);

		customerRegistrationDAO.save(customerRegistration);

	}

	public CustomerRegistrationDto findCustomerRegistrationDtoById(Long CustomerRegistrationId) {


		if (CustomerRegistrationId == null) {
			throw new IllegalArgumentException("REGISTRATION ID CANNOT BE NULL.");
		}

		CustomerRegistration customerRegistration = customerRegistrationDAO.findById(CustomerRegistrationId);

		if (customerRegistration == null) {
			throw new IllegalArgumentException("CUSTOMER REGISTRATION DETAILS NOT FOUND.");
		}

		CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();

		customerRegistrationDto.setCustomerRegistrationId(customerRegistration.getRegistrationId());
		customerRegistrationDto.setFirstName(customerRegistration.getFirstName());
		customerRegistrationDto.setLastName(customerRegistration.getLastName());
		customerRegistrationDto.setNic(customerRegistration.getNic());
		customerRegistrationDto.setPhone(customerRegistration.getPhone());
		customerRegistrationDto.setEmail(customerRegistration.getEmail());
		customerRegistrationDto.setAddress(customerRegistration.getAddress());
		customerRegistrationDto.setStatus(customerRegistration.getStatus());

		return customerRegistrationDto;

	}

	public List<CustomerRegistrationDto> getAllCustomerRegistrationDtos() {

		List<CustomerRegistration> customerRegistrations = customerRegistrationDAO.findAll();

		List<CustomerRegistrationDto> customerRegistrationDtos = new ArrayList<>();

		for (CustomerRegistration c : customerRegistrations) {
			CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();

			customerRegistrationDto.setCustomerRegistrationId(c.getRegistrationId());
			customerRegistrationDto.setFirstName(c.getFirstName());
			customerRegistrationDto.setLastName(c.getLastName());
			customerRegistrationDto.setNic(c.getNic());
			customerRegistrationDto.setPhone(c.getPhone());
			customerRegistrationDto.setEmail(c.getEmail());
			customerRegistrationDto.setAddress(c.getAddress());
			customerRegistrationDto.setStatus(c.getStatus());

			customerRegistrationDtos.add(customerRegistrationDto);
		}
		return customerRegistrationDtos;

	}


	public void updateRegistration(CustomerRegistrationDto customerRegistrationDto) {

		if(customerRegistrationDto == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS CANNOT BE NULL");
		}

		CustomerRegistration customerRegistration = customerRegistrationDAO.findById(customerRegistrationDto.getCustomerRegistrationId());

		if(customerRegistration == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS CANNOT BE FOUND");
		}

		customerRegistration.setFirstName(customerRegistrationDto.getFirstName());
		customerRegistration.setLastName(customerRegistrationDto.getLastName());
		customerRegistration.setNic(customerRegistrationDto.getNic());
		customerRegistration.setPhone(customerRegistrationDto.getPhone());
		customerRegistration.setEmail(customerRegistrationDto.getEmail());
		customerRegistration.setAddress(customerRegistrationDto.getAddress());

		customerRegistrationDAO.update(customerRegistration);

	}


	public void deleteRegistration(CustomerRegistrationDto customerRegistrationDto) {

		if(customerRegistrationDto == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS CANNOT BE NULL");
		}

		CustomerRegistration customerRegistration = customerRegistrationDAO.findById(customerRegistrationDto.getCustomerRegistrationId());

		if(customerRegistration == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS NOT FOUND");
		}

		customerRegistrationDAO.delete(customerRegistration);
	}


	public void approveRegistration(CustomerRegistrationApproveDto customerRegistrationApproveDto) {

		if(customerRegistrationApproveDto == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS CANNOT BE NULL");
		}

		CustomerRegistration customerRegistration = customerRegistrationDAO.findById(customerRegistrationApproveDto.getCustomerRegistrationId());

		if(customerRegistration == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS NOT FOUND");
		}

		customerRegistration.setStatus(RegistrationStatus.APPROVED);

		Customer customer = new Customer();

		customer.setFirstName(customerRegistration.getFirstName());
		customer.setLastName(customerRegistration.getLastName());
		customer.setNic(customerRegistration.getNic());
		customer.setPhone(customerRegistration.getPhone());
		customer.setEmail(customerRegistration.getEmail());
		customer.setAddress(customerRegistration.getAddress());

		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.save(customer);

		customerRegistrationDAO.update(customerRegistration);

		TemporaryPassword tp = new TemporaryPassword();
		String temporaryPassword = tp.generateTemporaryPassword();

		UserDAO userDao = new UserDAO();
		User user = new User();
		user.setRole("CUSTOMER");
		user.setUserName(customer.getNic());
		user.setPassWord(temporaryPassword);
		user.setCustomer(customer);

		userDao.save(user);

		EmailService emailService = new EmailService();
		emailService.sendCustomerCredentials(customer.getEmail(), user.getUserName(), temporaryPassword); //	(String email,String userName,String temporaryPassword)

	}

	public void rejectRegistration(CustomerRegistrationApproveDto customerRegistrationApproveDto) {

		if(customerRegistrationApproveDto == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS CANNOT BE NULL");
		}

		CustomerRegistration customerRegistration = customerRegistrationDAO.findById(customerRegistrationApproveDto.getCustomerRegistrationId());

		if(customerRegistration == null) {
			throw new IllegalArgumentException("REGISTRATION DETAILS NOT FOUND");
		}

		customerRegistration.setStatus(RegistrationStatus.REJECTED);

		customerRegistrationDAO.update(customerRegistration);
	}


	public boolean validateByNic(String nic) {
		boolean isFound=false;

		CustomerRegistration customerRegistration = customerRegistrationDAO.findByNic(nic);

		if(customerRegistration != null) {
			isFound = true;
		}
		return isFound;
	}

	public boolean hasActiveRegistration(String nic) {
		boolean isFound = false;

		CustomerRegistration customerRegistration =  customerRegistrationDAO.findActiveRegistrationByNic(nic);

		if(customerRegistration != null) {
			isFound = true;
		}
		return isFound;
	}



}
