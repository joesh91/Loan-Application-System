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
		
		System.out.println("TEST 1");
		
		String authorizationHeader = containerRequestContext.getHeaderString("Authorization");
		System.out.println("TEST 2");
		
		String path = containerRequestContext.getUriInfo().getPath();
		System.out.println("TEST 3");
		if(path.equals("/users/login")) {	// If the request is going to users/login, don't perform JWT authentication.
			System.out.println("TEST 4");
			return ;			
		}
		System.out.println("TEST 5");
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			containerRequestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build()
					);
			return;
		}
	
		
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
