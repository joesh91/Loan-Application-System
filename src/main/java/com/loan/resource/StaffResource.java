package com.loan.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.entity.Staff;
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
	public Response registerStaff(Staff staff) {

		if (staff == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		staffService.registerStaff(staff);
		return Response.status(Response.Status.CREATED).entity(staff).build();
	}

	// VIEW A STAFF MEMBER

	@GET
	@Path("/{id}")
	public Response searchStaff(@PathParam("id") int staffId) {

		Staff staff = staffService.findStaff(staffId);

		if (staff == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(staff).build();
	}

	// GET ALL STAFF MEMBERS

	@GET
	public Response getAllStaff() {

		List<Staff> staff = staffService.getAllStaff();

		return Response.ok(staff).build();
	}

	// UPDATE STAFF MEMBER

	@PUT
	@Path("/{id}")
	public Response updateStaff(@PathParam("id") int staffId, Staff staff) {

		if (staff == null) {

			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		staff.setStaffId(staffId);
		staffService.updateStaff(staff);

		return Response.ok(staff).build();
	}

	// DELETE STAFF MEMBER

	@DELETE
	@Path("/{id}")
	public Response deleteStaff(@PathParam("id") int staffId) {

		Staff staff = staffService.findStaff(staffId);

		if (staff == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		staffService.deleteStaff(staff);
		return Response.noContent().build();
	}

}
