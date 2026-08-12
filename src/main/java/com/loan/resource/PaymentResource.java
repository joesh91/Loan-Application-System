package com.loan.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.dto.PaymentDto;
import com.loan.dto.PaymentStatusRequest;
import com.loan.service.PaymentService;
import java.util.List;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

	PaymentService paymentService = new PaymentService();

	// MAKE PAYMENT

	@POST
	public Response makePayment(PaymentDto paymentDto) {

		if (paymentDto != null) {
			paymentService.makePayment(paymentDto);
			return Response.status(Response.Status.CREATED).entity(paymentDto).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// VIEW PAYMENT DETAILS

	@GET
	@Path("/{id}")
	public Response searchPayment(@PathParam("id") Long paymentID) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);

		if (paymentDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(paymentDto).build();
	}

	// GET ALL PAYMENTS

	@GET
	public Response getAllPayments() {

		List<PaymentDto> payments = paymentService.getAllPayments();

		return Response.ok(payments).build();
	}

	// UPDATE PAYMENT

	@PUT
	@Path("/{id}")
	public Response updatePayment(@PathParam("id") Long paymentID, PaymentDto paymentDto) {

		if (paymentDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		paymentDto.setPaymentId(paymentID);
		paymentService.updatePayment(paymentDto);

		return Response.ok(paymentDto).build();
	}

	// DELETE PAYMENT

	@DELETE
	@Path("/{id}")
	public Response deletePayment(@PathParam("id") Long paymentID) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);

		if (paymentDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		paymentService.deletePayment(paymentDto);
		return Response.noContent().build();
	}

	// MAKE A DECISION

	@PUT
	@Path("/{id}/status")
	public Response makeDecision(@PathParam("id") Long paymentID, PaymentStatusRequest request) {

		PaymentDto paymentDto = paymentService.findPayment(paymentID);

		if (paymentDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}	
		paymentService.makeDecision(paymentDto.getPaymentId(),request.getDecision());
		return Response.ok(paymentDto).build();
	}

}
