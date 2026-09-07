package com.loan.security;

import java.io.IOException;
import java.lang.reflect.Method;

import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ResourceInfo;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class RoleAuthorizationFilter implements ContainerRequestFilter {

	@jakarta.ws.rs.core.Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter (ContainerRequestContext containerRequestContext) throws IOException{
		
		Method method = resourceInfo.getResourceMethod();
		RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
		
		if(rolesAllowed == null) {
			return;
		}
		
		String [] allowedRoles = rolesAllowed.value();
		
		SecurityContext securityContext = containerRequestContext.getSecurityContext();
		
				for(String allowedRole : allowedRoles) {
					if(securityContext.isUserInRole(allowedRole)) {
						return;
					}
				}
		containerRequestContext.abortWith(
				
				Response.status(Response.Status.FORBIDDEN).build()
				);
	}
	
}
