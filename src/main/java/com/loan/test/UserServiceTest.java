package com.loan.test;

import java.util.List;
import com.loan.entity.User;
import com.loan.service.UserService;
import com.loan.entity.Customer;
import com.loan.service.CustomerService;

public class UserServiceTest {
	public static void main(String[] args) {

		// GENERATE USER SERVICE OBJECT

		UserService userService = new UserService();

		// GENERATE CUSTOMER OBJECT
		CustomerService customerService = new CustomerService();
		Customer customer3 = customerService.findCustomer(2);

		// GENERATE USER OBJECT

		User user1 = userService.findUser(2);

		user1.setUserName("TEST MERGED");
		userService.updateUser(user1);

		System.out.println(userService.findUser(2));

	}
}