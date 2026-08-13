package com.loan.exception;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
	
public Response toResponse(ConstraintViolationException exception) {
	
	Map <String,String> errors = new HashMap<>();
	
		for(ConstraintViolation <?> violation : exception.getConstraintViolations()) {
			
			String fieldName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			
			errors.put(fieldName, message);	
		}
	
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(errors)
				.build();	
	}

}