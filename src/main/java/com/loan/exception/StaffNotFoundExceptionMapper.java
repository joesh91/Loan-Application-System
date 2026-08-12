package com.loan.exception;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Provider
public class StaffNotFoundExceptionMapper implements ExceptionMapper<StaffNotFoundException> {
	
	@Override
	public Response toResponse(StaffNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
		.entity(exception.getMessage())
		.type(MediaType.APPLICATION_JSON)
		.build();
		
	}

}
