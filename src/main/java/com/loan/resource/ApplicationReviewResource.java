package com.loan.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.entity.ApplicationReview;
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
	public Response submitApplicationReview(ApplicationReview applicationReview) {

		if (applicationReview != null) {
			applicationReviewService.submitReview(applicationReview);
			return Response.status(Response.Status.CREATED).entity(applicationReview).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// SEARCH A REVIEW

	@GET
	@Path("/{id}")
	public Response searchApplicationReview(@PathParam("id") int applicationReviewID) {

		ApplicationReview applicationReview = applicationReviewService.searchReview(applicationReviewID);

		if (applicationReview != null) {
			return Response.ok(applicationReview).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	// GET ALL REVIEWS

	@GET
	public Response getAllApplicationReviews() {

		List<ApplicationReview> applicationReviews = applicationReviewService.getAllApplicationReview();

		return Response.ok(applicationReviews).build();

	}

	// UPDATE A REVIEW

	@PUT
	@Path("/{id}")
	public Response updateApplicationReview(@PathParam("id") int applicationReviewID,
			ApplicationReview applicationReview) {

		if (applicationReview == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		applicationReview.setReviewId(applicationReviewID);

		applicationReviewService.updateReview(applicationReview);

		return Response.ok(applicationReview).build();
	}

	// DELETE A REVIEW

	@DELETE
	@Path("/{id}")
	public Response deleteApplicationReview(@PathParam("id") int applicationReviewID) {

		ApplicationReview applicationReview = applicationReviewService.searchReview(applicationReviewID);
		if (applicationReview != null) {
			applicationReviewService.deleteReview(applicationReview);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
