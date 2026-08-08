package com.loan.resource;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import com.loan.service.LoanApplicationService;
import com.loan.entity.LoanApplication;

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
	public Response submitLoanApplication(LoanApplication loanApplication) {

		if (loanApplication != null) {
			loanApplicationService.submitLoanApplication(loanApplication);
			return Response.status(Response.Status.CREATED).entity(loanApplication).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}

	// SEARCH A LOAN APPLICATION BY ID

	@GET
	@Path("/{id}")
	public Response findLoanApplication(@PathParam("id") int loanApplicationID) {

		LoanApplication loanApplication = loanApplicationService.searchLoanApplication(loanApplicationID);

		if (loanApplication != null) {

			return Response.ok(loanApplication).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// SEARCH ALL LOAN APPLICATIONS

	@GET
	public Response getAllLoanApplications() {

		List<LoanApplication> loanApplicationList = loanApplicationService.getAllLoanApplications();

		if (loanApplicationList != null) {
			return Response.ok(loanApplicationList).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// UPDATE A LOAN APPLICATION

	@PUT
	@Path("/{id}")
	public Response updateLoanApplication(@PathParam("id") int loanApplicationID, LoanApplication loanApplication) {

		loanApplication.setApplicationId(loanApplicationID);

		loanApplicationService.updateLoanApplication(loanApplication);

		return Response.ok(loanApplication).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLoanApplication(@PathParam("id") int loanApplicationID) {

		LoanApplication loanApplication = loanApplicationService.searchLoanApplication(loanApplicationID);

		if (loanApplication != null) {
			loanApplicationService.deleteLoanApplication(loanApplication);
			return Response.noContent().build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

}