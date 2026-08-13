package com.loan.resource;

import com.loan.dto.ApplicationReviewDto;

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

		if (applicationReviewDto != null) {
			applicationReviewService.submitReview(applicationReviewDto);
			return Response.status(Response.Status.CREATED).entity(applicationReviewDto).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// SEARCH A REVIEW

	@GET
	@Path("/{id}")
	public Response searchApplicationReview(@PathParam("id") int applicationReviewId) {

		ApplicationReviewDto applicationReviewDto = applicationReviewService.searchReview(applicationReviewId);

		if (applicationReviewDto != null) {
			return Response.ok(applicationReviewDto).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
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
	public Response updateApplicationReview(@Valid @PathParam("id") Long applicationReviewId,
			ApplicationReviewDto applicationReviewDto) {

		if (applicationReviewDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		applicationReviewDto.setReviewId(applicationReviewId);

		applicationReviewService.updateReview(applicationReviewDto);

		return Response.ok(applicationReviewDto).build();
	}

	// DELETE A REVIEW

	@DELETE
	@Path("/{id}")
	public Response deleteApplicationReview(@PathParam("id") long applicationReviewID) {

		ApplicationReviewDto applicationReviewDto = applicationReviewService.searchReview(applicationReviewID);
		if (applicationReviewDto != null) {
			applicationReviewService.deleteReview(applicationReviewDto);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}







