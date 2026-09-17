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

System.out.println("JWT AUTHENTICATION CLASS IS GETTING CALLED : ");

		String authorizationHeader = containerRequestContext.getHeaderString("Authorization");

		String path = containerRequestContext.getUriInfo().getPath();

																									//	TEST CODE STEP
System.out.println("AUTHORIZATION HEADER : "+authorizationHeader);
System.out.println("PATH : "+path);

		if(path.equals("/users/login")) {	// If the request is going to users/login, don't perform JWT authentication.
			return ;
		}
																									//	TEST CODE STEP
System.out.println("JWT AUTHENTICATION CLASS : USER LOGIN PATH NOT SELECTED");

		if(path.equals("/registration") && containerRequestContext.getMethod().equals("POST")) {
			return;
		}

																									//	TEST CODE STEP
System.out.println("JWT AUTHENTICATION CLASS :REGISTRATION PATH NOT SELECTED");

	/*	if(path.startsWith("/registration/check-nic") && containerRequestContext.getMethod().equals("GET")) {
			return;
		}*/

																									//		TEST CODE STEP
System.out.println("JWT AUTHENTICATION CLASS : CHECK NIC IN REGISTRATION PATH NOT SELECTED");

	/*	if(path.startsWith("/registration") ) {
			return;
		}*/
																									//		TEST CODE STEP
System.out.println("JWT AUTHENTICATION CLASS : START WITH REGISTRATION PATH NOT SELECTED");

		/*if(path.startsWith("/registration/check-registration") && containerRequestContext.getMethod().equals("GET")) {
			return;
		}*/
																									//		TEST CODE STEP
System.out.println("JWT AUTHENTICATION CLASS : START WITH REGISTRATION PATH NOT SELECTED");


		if(path.equals("/users/verify-otp")) {
			return;
		}
		System.out.println("JWT AUTHENTICATION CLASS : PATH : "+path);
		
										// 	CHECK AUTHORIZATION DETAILS IN POSTMAN

		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			containerRequestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build()
					);
			return;
		}
System.out.println("JWT AUTHENTICATION CLASS : AUTHORIZATION HEADER IS NOT NULL OR START WITH BEAER KEYWORD");					//	TEST CODE STEP

		String token = authorizationHeader.substring(7);
System.out.println("JWT AUTHENTICATION CLASS : TOKEN : "+token);																//		TEST CODE STEP
		try {
		Claims claims = jwtUtil.validateToken(token);

		String userName = claims.getSubject();
		String role	= claims.get("role", String.class);


System.out.println("JWT AUTHENTICATION CLASS : ROLE : "+role);			
	//	TEST CODE STEP
		JwtSecurityContext jwtSecurityContext = new JwtSecurityContext(userName , role);

System.out.println("JWT SECURITY CONTEXT : "+jwtSecurityContext);									//	TEST CODE STEP
		containerRequestContext.setSecurityContext(jwtSecurityContext);

System.out.println( "AFTER SETTING CONTEXT = "+ containerRequestContext.getSecurityContext().getClass().getName()
			);							//	TEST CODE STEP
		}catch(Exception e) {
			containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
System.out.println("CONTAINER SECURITY CONTEXT ABORT WITH");										//	TEST CODE STEP
		}
	}
}
