package com.loan.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.entity.Payment;
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
	public Response makePayment(Payment payment) {

		if (payment != null) {
			paymentService.makePayment(payment);
			return Response.status(Response.Status.CREATED).entity(payment).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// VIEW PAYMENT DETAILS

	@GET
	@Path("/{id}")
	public Response searchPayment(@PathParam("id") int paymentID) {

		Payment payment = paymentService.findPayment(paymentID);

		if (payment == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(payment).build();
	}

	// GET ALL PAYMENTS

	@GET
	public Response getAllPayments() {

		List<Payment> payments = paymentService.getAllPayments();

		return Response.ok(payments).build();
	}

	// UPDATE PAYMENT

	@PUT
	@Path("/{id}")
	public Response updatePayment(@PathParam("id") int paymentID, Payment payment) {

		if (payment == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		payment.setPaymentId(paymentID);
		paymentService.updatePayment(payment);

		return Response.ok(payment).build();
	}

	// DELETE PAYMENT

	@DELETE
	@Path("/{id}")
	public Response deletePayment(@PathParam("id") int paymentID) {

		Payment payment = paymentService.findPayment(paymentID);

		if (payment == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		paymentService.deletePayment(payment);
		return Response.noContent().build();
	}

	// MAKE A DECISION

	@PUT
	@Path("/{id}/status")
	public Response makeDecision(@PathParam("id") int paymentID, PaymentStatusRequest request) {

		Payment payment = paymentService.findPayment(paymentID);

		if (payment == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		payment.setPaymentStatus(request.getDecision());
		paymentService.updatePayment(payment);
		return Response.ok(payment).build();
	}

}
