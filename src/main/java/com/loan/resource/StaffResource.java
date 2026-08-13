package com.loan.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.dto.StaffDto;
import com.loan.service.StaffService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import java.util.List;

@Path("/staffs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffResource {

	StaffService staffService = new StaffService();

	// REGISTER A STAFF MEMBER

	@POST
	public Response registerStaff(@Valid StaffDto staffDto) {

		staffService.registerStaff(staffDto);
		return Response.status(Response.Status.CREATED).entity(staffDto).build();
	}

	// VIEW A STAFF MEMBER

	@GET
	@Path("/{id}")
	public Response searchStaff(@PathParam("id") Long staffId) {

		StaffDto staffDto = staffService.findStaff(staffId);

		return Response.ok(staffDto).build();
	}

	// GET ALL STAFF MEMBERS

	@GET
	public Response getAllStaff() {

		List<StaffDto> staffDto = staffService.getAllStaff();

		return Response.ok(staffDto).build();
	}

	// UPDATE STAFF MEMBER

	@PUT
	@Path("/{id}")
	public Response updateStaff(@PathParam("id") Long staffId, @Valid StaffDto staffDto) {

		staffDto.setStaffId(staffId);
		staffService.updateStaff(staffDto);

		return Response.ok(staffDto).build();
	}

	// DELETE STAFF MEMBER

	@DELETE
	@Path("/{id}")
	public Response deleteStaff(@PathParam("id") Long staffId) {

		StaffDto staffDto = staffService.findStaff(staffId);

		staffService.deleteStaff(staffDto);
		return Response.noContent().build();
	}

}
