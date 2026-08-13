package com.loan.resource;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Valid;

import com.loan.service.LoanApplicationService;
import com.loan.dto.LoanApplicationDto;

import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/loanApplications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoanApplicationResource {

	// GENEREATE LOAN APPLICATION SERVICE OBJECT

	LoanApplicationService loanApplicationService = new LoanApplicationService();

	// SUBMIT LOAN APPLICATION FROM SERVICE LAYER
	
	@POST
	public Response submitLoanApplication(@Valid LoanApplicationDto loanApplicationDto) {

		if(loanApplicationDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		loanApplicationService.submitLoanApplication(loanApplicationDto);
		return Response.status(Response.Status.CREATED).entity(loanApplicationDto).build();
		
	}
	
	
	// SEARCH A LOAN APPLICATION BY ID

	@GET
	@Path("/{id}")
	public Response findLoanApplication(@PathParam("id") Long loanApplicationId) {

		LoanApplicationDto loanApplicationDto = loanApplicationService.searchLoanApplication(loanApplicationId);

		if (loanApplicationDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.status(Response.Status.FOUND).entity(loanApplicationDto).build();
				
	}	
				
	// SEARCH ALL LOAN APPLICATIONS

	@GET
	public Response getAllLoanApplications() {

		List<LoanApplicationDto> loanApplicationListdto = loanApplicationService.getAllLoanApplications();

		if (loanApplicationListdto != null) {
			return Response.ok(loanApplicationListdto).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// UPDATE A LOAN APPLICATION

	@PUT
	@Path("/{id}")
	public Response updateLoanApplication(@Valid @PathParam("id") Long loanApplicationID, LoanApplicationDto loanApplicationDto) {

		loanApplicationDto.setApplicationId(loanApplicationID);

		loanApplicationService.updateLoanApplication(loanApplicationDto);

		return Response.ok(loanApplicationDto).entity(loanApplicationDto).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLoanApplication(@PathParam("id") Long loanApplicationID) {

		LoanApplicationDto loanApplicationDto = loanApplicationService.searchLoanApplication(loanApplicationID);

		if (loanApplicationDto != null) {
			loanApplicationService.deleteLoanApplication(loanApplicationDto);
			return Response.noContent().build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

}