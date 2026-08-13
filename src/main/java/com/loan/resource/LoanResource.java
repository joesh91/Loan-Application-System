package com.loan.resource;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Valid;

import com.loan.service.LoanService;
import com.loan.dto.LoanDecisionDto;
import com.loan.dto.LoanDto;

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
	public Response registerLoan(@Valid LoanDto loanDto) {

		loanService.submitLoan(loanDto);
		return Response.status(Response.Status.CREATED).entity(loanDto).build();
	}

	// VIEW A LOAN

	@GET
	@Path("/{id}")
	public Response searchLoan(@PathParam("id") Long loanId) {

		LoanDto loanDto = loanService.findLoan(loanId);
		return Response.ok(loanDto).build();

	}

	// VIEW ALL LOANS

	@GET
	public Response getAllLoans() {
		List<LoanDto> loans = loanService.getAllLoans();
		return Response.ok(loans).build();

	}

	// UPDATE A LOAN

	@PUT
	@Path("/{id}")
	public Response updateLoan(@PathParam("id") Long loanId, @Valid LoanDto loanDto) {

		loanDto.setLoanId(loanId);
		loanService.updateLoan(loanDto);
		return Response.ok(loanDto).build();

	}

	// DELETE A LOAN

	@DELETE
	@Path("/{id}")
	public Response deleteLoan(@PathParam("id") Long loanId) {

		LoanDto loanDto = loanService.findLoan(loanId);
		loanService.deleteLoan(loanDto);
		return Response.noContent().build();

	}

	@PUT
	@Path("/decision")
	public Response makeDecision(@Valid LoanDecisionDto loanDecisionDto) {

		loanService.makeDecision(loanDecisionDto);
		return Response.ok(loanDecisionDto).build();
	}

}