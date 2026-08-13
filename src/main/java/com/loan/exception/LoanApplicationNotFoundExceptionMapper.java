package com.loan.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoanApplicationNotFoundExceptionMapper implements ExceptionMapper<LoanApplicationNotFoundException> {
	
	@Override
	public Response toResponse(LoanApplicationNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
