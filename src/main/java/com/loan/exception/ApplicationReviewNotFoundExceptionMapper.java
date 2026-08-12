package com.loan.exception;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Provider
public class ApplicationReviewNotFoundExceptionMapper implements ExceptionMapper<ApplicationReviewNotFoundException> {
	
	@Override
	public Response toResponse(ApplicationReviewNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception)
				.type(MediaType.APPLICATION_JSON)
				.build();
		
	}


}
