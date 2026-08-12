package com.loan.service;

import java.util.List;
import java.util.ArrayList;
import com.loan.dao.CustomerDAO;
import com.loan.entity.Customer;
import com.loan.exception.CustomerNotFoundException;
import com.loan.dto.CustomerDto;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();

	// REGISTER THIS CUSTOMER

	public void registerCustomer(CustomerDto customerDto) {
		
			Customer customer = new Customer();
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setAddress(customerDto.getAddress());
			customer.setNic(customerDto.getNic());
			customer.setPhone(customerDto.getPhone());
			customer.setEmail(customerDto.getEmail());
			
		
		customerDAO.save(customer);
	}

	// FIND A CUSTOMER

		public CustomerDto findCustomer(Long customerID) {

			Customer customer =  customerDAO.findById(customerID);
			
			if(customer == null) {
				throw new CustomerNotFoundException("CUSTOMER ID "+ customerID  + " IS NOT FOUND.");
			}
			CustomerDto  customerDto = new CustomerDto();
			
			customerDto.setCustomerID(customer.getCustomerId());
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setNic(customer.getNic());
			customerDto.setAddress(customer.getAddress());
			customerDto.setEmail(customer.getEmail());
			customerDto.setPhone(customer.getPhone());
			
			return customerDto;
		}

	// GET ALL CUSTOMERS

	public List<CustomerDto> getAllCustomers() {
		
		List<Customer> customers = customerDAO.findAll();
		
		List<CustomerDto> customerDtos = new ArrayList<>();
		
		for(Customer customer : customers) {
			
			CustomerDto customerDto = new CustomerDto();
			
			customerDto.setCustomerID(customer.getCustomerId());
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setNic(customer.getNic());
			customerDto.setAddress(customer.getAddress());
			customerDto.setEmail(customer.getEmail());
			customerDto.setPhone(customer.getPhone());
			
			
			customerDtos.add(customerDto);
		}

		return customerDtos;
	}

	// UPDATE THE CUSTOMER

	public void updateCustomer(CustomerDto customerDto) {
			
			if(customerDto == null) {
				throw new CustomerNotFoundException("CUSTOMER DETAILS ARE NOT FOUND.");
			}
			
			Customer existingCustomer = customerDAO.findById(customerDto.getCustomerID());
			
				if(existingCustomer == null) {
					throw new CustomerNotFoundException("CUSTOMER DETAILS ARE FOR CUSTOMER ID :  " +customerDto.getCustomerID()+ " NOT FOUND.");
				}
				
				existingCustomer.setFirstName(customerDto.getFirstName());
				existingCustomer.setLastName(customerDto.getLastName());
				existingCustomer.setNic(customerDto.getNic());
				existingCustomer.setAddress(customerDto.getAddress());
				existingCustomer.setEmail(customerDto.getEmail());
				existingCustomer.setPhone(customerDto.getPhone());
		
		
		customerDAO.update(existingCustomer);
	}

	// DELETE THE CUSTOMER

	public void deleteCustomer(CustomerDto customerDto) {
		
			if(customerDto == null){
				throw new CustomerNotFoundException("CUSTOMER DETAILS ARE NOT FOUND.");
			}

			Customer deletingCustomer = customerDAO.findById(customerDto.getCustomerID());
				
			if(deletingCustomer == null ) {
				throw new CustomerNotFoundException("CUSTOMER DETAILS ARE FOR CUSTOMER ID :  " +customerDto.getCustomerID()+ " NOT FOUND.");
			}
			
		customerDAO.delete(deletingCustomer);
	}

}
