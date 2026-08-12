package com.loan.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class LoanNotFoundExceptionMapper implements ExceptionMapper<LoanNotFoundException> {
	
	public Response toResponse(LoanNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception.getMessage())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
