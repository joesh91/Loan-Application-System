package com.loan.exception;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PaymentNotFoundExceptionMapper implements ExceptionMapper<PaymentNotFoundException> {
	
	public Response toResponse(PaymentNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
