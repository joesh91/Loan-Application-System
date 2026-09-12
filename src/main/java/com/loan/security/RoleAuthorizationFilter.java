package com.loan.security;

import java.io.IOException;
import java.lang.reflect.Method;

import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class RoleAuthorizationFilter implements ContainerRequestFilter {

	@jakarta.ws.rs.core.Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter (ContainerRequestContext containerRequestContext) throws IOException{
System.out.println("ROLES AUTHORIZATION CLASS IS ACCESSED");										// 	 TEST CASE
		Method method = resourceInfo.getResourceMethod();

System.out.println("METHOD NAME : "+method);														// 	 TEST CASE
		RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);


System.out.println("ROLES ALLOWED : "+rolesAllowed);												// 	 TEST CASE

		if(rolesAllowed == null) {
			return;
		}

		String [] allowedRoles = rolesAllowed.value();
	
System.out.println("ALLOWED ROLES : "+allowedRoles[0]+" <> "+allowedRoles[1]);

// 	 TEST CASE
		SecurityContext securityContext = containerRequestContext.getSecurityContext();
			
		String result = securityContext.getClass().getName();
System.out.println("RESULT  : "+result );
		
System.out.println("SECURITY CONTEXT : "+securityContext);
System.out.println("SECURITY CONTEXT CLASS = " + securityContext.getClass().getName());
				for(String allowedRole : allowedRoles) {
					
System.out.println("FOR LOOP 1");					
					if(securityContext.isUserInRole(allowedRole)==true) {
System.out.println("FOR LOOP 2");							
						return;
					}
				}

		containerRequestContext.abortWith(
				Response.status(Response.Status.FORBIDDEN).build()
				);
System.out.println("SECURITY CONTEXT ABORTED WITH / REQUEST DENIED : ");												// 	 TEST CASE
	}

}
