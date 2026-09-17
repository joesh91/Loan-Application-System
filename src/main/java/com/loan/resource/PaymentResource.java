package com.loan.resource;

import java.util.List;

import com.loan.dto.PaymentDto;
import com.loan.dto.PaymentStatusRequest;
import com.loan.service.PaymentService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

	PaymentService paymentService = new PaymentService();

	// MAKE PAYMENT

	@POST
	@RolesAllowed({"CUSTOMER"})
	public Response makePayment(@Valid PaymentDto paymentDto) {

		paymentService.makePayment(paymentDto);
		return Response.status(Response.Status.CREATED).entity(paymentDto).build();

	}

	// VIEW PAYMENT DETAILS

	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response searchPayment(@PathParam("id") Long paymentID) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);
		return Response.ok(paymentDto).build();
	}

	// GET ALL PAYMENTS

	@GET
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response getAllPayments() {
		System.out.println("PYAMENT RESOURCE CLASS GET ALL METHOD");

		List<PaymentDto> payments = paymentService.getAllPayments();
		return Response.ok(payments).build();
	}

	// UPDATE PAYMENT

	@PUT
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response updatePayment(@PathParam("id") Long paymentID, @Valid PaymentDto paymentDto) {

		paymentDto.setPaymentId(paymentID);
		paymentService.updatePayment(paymentDto);
		return Response.ok(paymentDto).build();
	}

	// DELETE PAYMENT

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response deletePayment(@PathParam("id") Long paymentID) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);
		paymentService.deletePayment(paymentDto);
		return Response.noContent().build();
	}

	// MAKE A DECISION

	@PUT
	@Path("/{id}/status")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response makeDecision(@PathParam("id") Long paymentID, @Valid PaymentStatusRequest request) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);
		paymentService.makeDecision(paymentDto.getPaymentId(), request.getDecision());
		return Response.ok(paymentDto).build();
	}

}
