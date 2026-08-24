package com.loan.security;

import io.jsonwebtoken.Claims;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JwtAuthenticationFilter implements ContainerRequestFilter{
	
	private JwtUtil jwtUtil = new JwtUtil();

	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		
		System.out.println("FILTER START	 : "+containerRequestContext);
		
		String authorizationHeader = containerRequestContext.getHeaderString("Authorization");
		System.out.println("TEST 2 : "+authorizationHeader);
				
		String path = containerRequestContext.getUriInfo().getPath();
		System.out.println("TEST 3 : " + path);
		
		if(path.equals("/users/login")) {	// If the request is going to users/login, don't perform JWT authentication.
			System.out.println("TEST 4 : ");
			return ;			
		}
		System.out.println("TEST 5 : ");
		
		
	if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			containerRequestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build()
					);System.out.println("TEST 6 : NULL");
			return;
		}
	System.out.println("TEST 7 : ");
		String token = authorizationHeader.substring(7);
		System.out.println("TEST 8 : "+token);
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
