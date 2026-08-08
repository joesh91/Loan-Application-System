package com.loan.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.entity.User;
import com.loan.service.UserService;
import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	UserService userService = new UserService();

	// REGISTER USER

	@POST
	public Response registerUser(User user) {

		if (user == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		userService.registerUser(user);
		return Response.status(Response.Status.CREATED).entity(user).build();
	}

	// VIEW USER

	@GET
	@Path("/{id}")
	public Response searchUser(@PathParam("id") int userId) {

		User user = userService.findUser(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	// GET ALL USERS

	@GET
	public Response getAllUsers() {

		List<User> users = userService.getAllUsers();

		return Response.ok(users).build();

	}

	// UPDATE USER

	@PUT
	@Path("/{id}")
	public Response updateUser(@PathParam("id") int userId, User user) {

		if (user == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		user.setUserId(userId);
		userService.updateUser(user);

		return Response.ok(user).build();
	}

	// DELETE USER

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") int userId) {

		User user = userService.findUser(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		userService.deleteUser(user);
		return Response.noContent().build();
	}

}
