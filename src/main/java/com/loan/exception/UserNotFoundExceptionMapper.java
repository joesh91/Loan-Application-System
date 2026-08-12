package com.loan.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
	
	public Response toResponse(UserNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
