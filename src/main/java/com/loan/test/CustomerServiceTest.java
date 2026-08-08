package com.loan.test;

import java.time.LocalDateTime;

import com.loan.service.CustomerService;
import com.loan.dao.CustomerDAO;
import com.loan.entity.Customer;

public class CustomerServiceTest {
	public static void main(String[] args) {

		CustomerService customerService = new CustomerService();
		Customer customer = new Customer();

		customer.setFirstName("SERVICE TEST123");
		customer.setLastName("SERVICE TEST123");
		customer.setNic("SERVI123");
		customer.setAddress("SERVICE TEST123");
		customer.setPhone("S123");
		customer.setEmail("SER123");

		customerService.registerCustomer(customer);

		/*
		 * Customer customer1 = customerService.findCustomer(9);
		 * System.out.println(customer1.toString());
		 * 
		 * System.out.println(customerService.getAllCustomers());
		 * 
		 * 
		 * customer = customerService.findCustomer(9);
		 * customer.setFirstName("TEST TWO"); customerService.updateCustomer(customer);
		 * 
		 * customer = customerService.findCustomer(9);
		 * customerService.deleteCustomer(customer);
		 */

	}
}