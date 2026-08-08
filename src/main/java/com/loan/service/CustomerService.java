package com.loan.service;

import java.util.List;
import com.loan.dao.CustomerDAO;
import com.loan.entity.Customer;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();

	// REGISTER THIS CUSTOMER

	public void registerCustomer(Customer customer) {

		customerDAO.save(customer);
	}

	// FIND A CUSTOMER

	public Customer findCustomer(int customerID) {

		return customerDAO.findById(customerID);

	}

	// GET ALL CUSTOMERS

	public List<Customer> getAllCustomers() {

		return customerDAO.findAll();
	}

	// UPDATE THE CUSTOMER

	public void updateCustomer(Customer customer) {
		customerDAO.update(customer);
	}

	// DELETE THE CUSTOMER

	public void deleteCustomer(Customer customer) {

		customerDAO.delete(customer);
	}

}
