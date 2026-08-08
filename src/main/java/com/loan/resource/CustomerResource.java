package com.loan.resource;

import jakarta.ws.rs.Produces;
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
import com.loan.entity.Customer;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	CustomerService customerService = new CustomerService();

	// NEW CUSTOMER REGISTRATION

	@POST
	public Response registerCustomer(Customer customer) {

		customerService.registerCustomer(customer);

		return Response.status(Response.Status.CREATED).entity(customer).build();
	}

	// SEARCH CUSTOMER BY ID

	@GET
	@Path("/{id}")
	public Response findCustomer(@PathParam("id") int customerID) {

		Customer customer = customerService.findCustomer(customerID);

		if (customer != null) {
			return Response.ok(customer).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();

	}

	// GET ALL CUSTOMERS

	@GET
	public Response getAllCustomers() {

		List<Customer> customerList = customerService.getAllCustomers();

		return Response.ok(customerList).build();
	}

	// UPDATE CUSTOMER DETAILS

	@PUT
	@Path("/{id}")
	public Response updateCustomer(@PathParam("id") int customerID, Customer customer) {

		customer.setCustomerId(customerID);

		customerService.updateCustomer(customer);

		return Response.ok(customer).build();
	}

	// DELETE CUSTOMER

	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") int customerID) {

		Customer customer = customerService.findCustomer(customerID);

		if (customer != null) {
			customerService.deleteCustomer(customer);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
