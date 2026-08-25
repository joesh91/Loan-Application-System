package com.loan.config;

import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

import com.loan.resource.CustomerResource;
import com.loan.resource.LoanApplicationResource;
import com.loan.resource.UserResource;
import com.loan.security.JwtAuthenticationFilter;
import com.loan.security.RoleAuthorizationFilter;

import jakarta.ws.rs.ApplicationPath;

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

		
		return classes;
	}
}
