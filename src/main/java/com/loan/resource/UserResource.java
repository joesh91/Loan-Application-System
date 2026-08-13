package com.loan.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.loan.dto.UserDto;
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
	public Response registerUser(@Valid UserDto userDto) {

		if (userDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		userService.registerUser(userDto);
		return Response.status(Response.Status.CREATED).entity(userDto).build();
	}

	// VIEW USER

	@GET
	@Path("/{id}")
	public Response searchUser(@PathParam("id") Long userId) {

		UserDto userDto = userService.findUser(userId);
		if (userDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(userDto).build();
	}

	// GET ALL USERS

	@GET
	public Response getAllUsers() {

		List<UserDto> usersDtos = userService.getAllUsers();

		return Response.ok(usersDtos).build();

	}

	// UPDATE USER

	@PUT
	@Path("/{id}")
	public Response updateUser(@Valid @PathParam("id") Long userId ,UserDto userDto) {
		
		if(userDto == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		userDto.setUserId(userId);
		userService.updateUser(userDto);
		
		return Response.ok(userDto).build();
	}
		
	

	// DELETE USER

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Long userId) {

		UserDto userDto = userService.findUser(userId);
		if (userDto == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		userService.deleteUser(userDto);
		return Response.noContent().build();
	}

}
