package com.loan.config;

import java.util.HashSet;
import java.util.Set;

import com.loan.resource.ApplicationReviewResource;
import com.loan.resource.CustomerRegistrationResource;
import com.loan.resource.CustomerResource;
import com.loan.resource.LoanApplicationResource;
import com.loan.resource.LoanResource;
import com.loan.resource.PaymentResource;
import com.loan.resource.StaffResource;
import com.loan.resource.UserResource;
import com.loan.security.JwtAuthenticationFilter;
import com.loan.security.RoleAuthorizationFilter;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {


	@Override
	public Set<Class<?>> getClasses(){

		Set<Class<?>> classes = new HashSet<>();

		classes.add(JwtAuthenticationFilter.class);
		classes.add(RoleAuthorizationFilter.class);

		// REST RESOURCE CLASSES

		classes.add(UserResource.class);
		classes.add(CustomerResource.class);
		classes.add(LoanApplicationResource.class);
		classes.add(CustomerRegistrationResource.class);
		classes.add(StaffResource.class);
		classes.add(ApplicationReviewResource.class);
		classes.add(LoanResource.class);
		classes.add(PaymentResource.class);


		return classes;
	}
}
