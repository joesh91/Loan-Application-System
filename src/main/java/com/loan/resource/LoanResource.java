package com.loan.resource;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import com.loan.entity.Loan;
import com.loan.service.LoanService;

import java.util.List;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

	LoanService loanService = new LoanService();

	// CREATE A LOAN

	@POST
	public Response registerLoan(Loan loan) {

		if (loan != null) {
			loanService.submitLoan(loan);
			return Response.status(Response.Status.CREATED).entity(loan).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// VIEW A LOAN

	@GET
	@Path("/{id}")
	public Response searchLoan(@PathParam("id") int loanId) {

		Loan loan = loanService.findLoan(loanId);
		if (loan != null) {
			return Response.ok(loan).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// VIEW ALL LOANS
	@GET
	public Response getAllLoans() {

		List<Loan> loans = loanService.getAllLoans();

		if (loans != null) {
			return Response.ok(loans).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// UPDATE A LOAN

	@PUT
	@Path("/{id}")
	public Response updateLoan(@PathParam("id") int loanId, Loan loan) {

		if (loan == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		loan.setLoanId(loanId);

		loanService.updateLoan(loan);

		return Response.ok(loan).build();

	}

	// DELETE A LOAN

	@DELETE
	@Path("/{id}")
	public Response deleteLoan(@PathParam("id") int loanId) {

		Loan loan = loanService.findLoan(loanId);
		if (loan != null) {
			loanService.deleteLoan(loan);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}