package com.loan.resource;

import com.loan.dto.ApplicationReviewDto;
import com.loan.dto.LoanApplicationReviewDecision;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.service.ApplicationReviewService;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("applicationreviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationReviewResource {

	ApplicationReviewService applicationReviewService = new ApplicationReviewService();

	// SUBMIT AN REVIEW

	@POST
	public Response submitApplicationReview(@Valid ApplicationReviewDto applicationReviewDto) {

			applicationReviewService.submitReview(applicationReviewDto);
			return Response.status(Response.Status.CREATED).entity(applicationReviewDto).build();
	
	}

	// SEARCH A REVIEW

	@GET
	@Path("/{id}")
	public Response searchApplicationReview(@PathParam("id") Long applicationReviewId) {

			ApplicationReviewDto applicationReviewDto = applicationReviewService.searchReview(applicationReviewId);
			return Response.ok(applicationReviewDto).build();
		
	}

	// GET ALL REVIEWS

	@GET
	public Response getAllApplicationReviews() {

		List<ApplicationReviewDto> applicationReviewsDtos = applicationReviewService.getAllApplicationReview();
		return Response.ok(applicationReviewsDtos).build();
	}

	// UPDATE A REVIEW

	@PUT
	@Path("/{id}")
	public Response updateApplicationReview(@PathParam("id") Long applicationReviewId,
											@Valid ApplicationReviewDto applicationReviewDto) {

		applicationReviewDto.setReviewId(applicationReviewId);
		applicationReviewService.updateReview(applicationReviewDto);
		return Response.ok(applicationReviewDto).build();
	}

	// DELETE A REVIEW

	@DELETE
	@Path("/{id}")
	public Response deleteApplicationReview(@PathParam("id") Long applicationReviewID) {

			ApplicationReviewDto applicationReviewDto = applicationReviewService.searchReview(applicationReviewID);
			applicationReviewService.deleteReview(applicationReviewDto);
			return Response.noContent().build();
	
	}
	
	
	// MAKE A DECISION
	@PUT
	@Path("/{id}/decision")
	public Response makeDecision(@PathParam("id") Long applicationReviewId, LoanApplicationReviewDecision loanApplicationReviewDecision) {
		
			ApplicationReviewDto applicationReviewDto = applicationReviewService.searchReview(applicationReviewId);
			applicationReviewService.makeDecision(loanApplicationReviewDecision.getDecision().name(),applicationReviewDto );
			return Response.ok(applicationReviewDto).build();
	}
	
}
