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

		if (loanDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		loanService.submitLoan(loanDto);
		return Response.status(Response.Status.CREATED).entity(loanDto).build();
	}

	// VIEW A LOAN

	@GET
	@Path("/{id}")
	public Response searchLoan(@PathParam("id") Long loanId) {

		LoanDto loanDto = loanService.findLoan(loanId);
		if (loanDto != null) {
			return Response.ok(loanDto).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// VIEW ALL LOANS
	
	@GET
	public Response getAllLoans() {
		List<LoanDto> loans = loanService.getAllLoans();

		if (loans != null) {
			return Response.ok(loans).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	

	// UPDATE A LOAN

	@PUT
	@Path("/{id}")
	public Response updateLoan(@Valid @PathParam("id") Long loanId, LoanDto loanDto) {

		if (loanDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		loanDto.setLoanId(loanId);
		
		loanService.updateLoan(loanDto);
		
		return Response.ok(loanDto).build();

	}

	// DELETE A LOAN

	@DELETE
	@Path("/{id}")
	public Response deleteLoan(@PathParam("id") Long loanId) {

		LoanDto loanDto = loanService.findLoan(loanId);
		if (loanDto != null) {
			loanService.deleteLoan(loanDto);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/decision")
	public Response makeDecision(@Valid LoanDecisionDto loanDecisionDto) {
		
		if(loanDecisionDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		loanService.makeDecision(loanDecisionDto);
		return Response.ok(loanDecisionDto).build();
	}

}