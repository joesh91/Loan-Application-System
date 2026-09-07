package com.loan.security;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter{
	
	private JwtUtil jwtUtil = new JwtUtil();

	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		
		String authorizationHeader = containerRequestContext.getHeaderString("Authorization");
		
		String path = containerRequestContext.getUriInfo().getPath();
System.out.println("TEST 1");

		if(path.equals("/users/login")) {	// If the request is going to users/login, don't perform JWT authentication.

			return ;			
		}
System.out.println("TEST 2");	
		if(path.equals("/registration") && containerRequestContext.getMethod().equals("POST")) {
			return;
		}
		
		if(path.startsWith("/registration/check-nic") && containerRequestContext.getMethod().equals("GET")) {
			return;
		}
		
		// TEMPORARY
		if(path.startsWith("/registration") ) {
			return;
		}
		
		if(path.startsWith("/registration/check-registration") && containerRequestContext.getMethod().equals("GET")) {
			return;
		}
		System.out.println("TEST 3");		
		if(path.equals("/users/verify-otp")) {
			return;
		}
		System.out.println("PATH : "+path);
		
System.out.println("TEST 3");		

		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			containerRequestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build()
					);
			return;
		}
System.out.println("TEST 4");	
		
		String token = authorizationHeader.substring(7);
	
		try {
		Claims claims = jwtUtil.validateToken(token);
		
		String userName = claims.getSubject();
		String role	= claims.get("role", String.class);
		
		JwtSecurityContext jwtSecurityContext = new JwtSecurityContext(userName , role);
		
		containerRequestContext.setSecurityContext(jwtSecurityContext);
			
		}catch(Exception e) {
			containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}
