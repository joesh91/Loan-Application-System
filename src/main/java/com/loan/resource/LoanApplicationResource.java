package com.loan.resource;

import java.util.List;

import com.loan.dto.LoanApplicationDto;
import com.loan.service.LoanApplicationService;

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

@Path("/loanApplications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoanApplicationResource {

	// GENEREATE LOAN APPLICATION SERVICE OBJECT

	LoanApplicationService loanApplicationService = new LoanApplicationService();

	// SUBMIT LOAN APPLICATION FROM SERVICE LAYER

	@POST
	@RolesAllowed({"CUSTOMER" ,"MANAGER"})
	public Response submitLoanApplication(@Valid LoanApplicationDto loanApplicationDto) {

		loanApplicationService.submitLoanApplication(loanApplicationDto);
		return Response.status(Response.Status.CREATED).entity(loanApplicationDto).build();

	}

	// SEARCH A LOAN APPLICATION BY ID

	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response findLoanApplication(@PathParam("id") Long loanApplicationId) {

		LoanApplicationDto loanApplicationDto = loanApplicationService.searchLoanApplication(loanApplicationId);
		return Response.status(Response.Status.FOUND).entity(loanApplicationDto).build();

	}

	// SEARCH ALL LOAN APPLICATIONS

	@GET
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response getAllLoanApplications() {

		List<LoanApplicationDto> loanApplicationListdto = loanApplicationService.getAllLoanApplications();
		return Response.ok(loanApplicationListdto).build();
	}

	// UPDATE A LOAN APPLICATION

	@PUT
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response updateLoanApplication(@PathParam("id") Long loanApplicationID, @Valid LoanApplicationDto loanApplicationDto) {

		loanApplicationDto.setApplicationId(loanApplicationID);
		loanApplicationService.updateLoanApplication(loanApplicationDto);
		return Response.ok(loanApplicationDto).entity(loanApplicationDto).build();
	}

	// DELETE LOAN APPLICATION

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response deleteLoanApplication(@PathParam("id") Long loanApplicationID) {

		LoanApplicationDto loanApplicationDto = loanApplicationService.searchLoanApplication(loanApplicationID);
		loanApplicationService.deleteLoanApplication(loanApplicationDto);
		return Response.noContent().build();

	}

}