package com.loan.exception;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider			// Registers this class with JAX-RS/RESTEasy as an exception provider
					//Handles ConstraintViolationException and converts it into an HTTP response
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override // Called automatically by RESTEasy when a ConstraintViolationException occurs
	public Response toResponse(ConstraintViolationException exception) {

		 // Creates a Map to store validation errors as field name and error message
		Map<String, String> errors = new HashMap<>();

		  // Loops through all validation errors contained in the exception
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {

			String fieldName = violation.getPropertyPath().toString();
			String message = violation.getMessage();

			errors.put(fieldName, message);
		}

		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(errors).build();
	}

}