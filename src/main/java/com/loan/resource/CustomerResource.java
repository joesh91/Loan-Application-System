package com.loan.resource;

import jakarta.ws.rs.Produces;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.Response;

import com.loan.service.CustomerService;
import com.loan.dto.CustomerDto;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	CustomerService customerService = new CustomerService();


	// NEW CUSTOMER REGISTRATION
	
	@POST
	public Response registerCustomer(@Valid CustomerDto customerDto) {
	
		customerService.registerCustomer(customerDto);
		return Response.status(Response.Status.CREATED)
				.entity(customerDto)
				.build();
	}


	// SEARCH CUSTOMER BY ID

	@GET
	@Path("/{id}")
	public Response findCustomer(@PathParam("id") Long customerID) {

			CustomerDto customer = customerService.findCustomer(customerID);
			return Response.ok(customer).build();

	}

	// GET ALL CUSTOMERS

	@GET
	public Response getAllCustomers() {

		List<CustomerDto> customerList = customerService.getAllCustomers();
		return Response.ok(customerList).build();
	}

	// UPDATE CUSTOMER DETAILS

	@PUT
	@Path("/{id}")
	public Response updateCustomer( @PathParam("id") Long customerID,  @Valid CustomerDto customerDto) {

		customerDto.setCustomerID(customerID);
		customerService.updateCustomer(customerDto);
		return Response.ok(customerDto).build();
	}

	// DELETE CUSTOMER

	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") Long customerID) {

		CustomerDto customer = customerService.findCustomer(customerID);

			customerService.deleteCustomer(customer);
			return Response.noContent().build();
		
	}

}
