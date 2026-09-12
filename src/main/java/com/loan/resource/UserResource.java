package com.loan.resource;

import java.util.List;

import com.loan.dto.LoginRequestDto;
import com.loan.dto.OtpVerificaionDto;
import com.loan.dto.UserDto;
import com.loan.dto.UserRoleChangeDto;
import com.loan.service.UserService;

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

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	UserService userService = new UserService();

	// REGISTER USER

	@POST
	@RolesAllowed({"ADMIN" , "MANAGER"})
	public Response registerUser(@Valid UserDto userDto) {

		userService.registerUser(userDto);
		return Response.status(Response.Status.CREATED).entity(userDto).build();
	}

	// VIEW USER

	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response searchUser(@PathParam("id") Long userId) {

		UserDto userDto = userService.findUser(userId);
		return Response.ok(userDto).build();
	}

	// GET ALL USERS

	@GET
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response getAllUsers() {

		List<UserDto> usersDtos = userService.getAllUsers();
		return Response.ok(usersDtos).build();

	}

	// UPDATE USER

	@PUT
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response updateUser(@PathParam("id") Long userId, @Valid UserDto userDto) {

		userDto.setUserId(userId);
		userService.updateUser(userDto);
		return Response.ok(userDto).build();
	}

	// DELETE USER

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response deleteUser(@PathParam("id") Long userId) {

		System.out.println("TEST DELETE");

		UserDto userDto = userService.findUser(userId);
		userService.deleteUser(userDto);
		return Response.noContent().build();
	}

	// AUTHENTICATE USER

	@POST
	@Path("/login")
	public Response login(LoginRequestDto loginRequestDto) {

		userService.login(loginRequestDto);

		return Response.ok("OTP SENT").build();


	}

	//	VERIFY OTP

	@POST
	@Path("/verify-otp")
	public Response verifyOtp(OtpVerificaionDto otpVerificationDto) {

		String token = userService.verifyOtpAndGenerateToken(otpVerificationDto.getUserName(),otpVerificationDto.getEnteredOtp());

		if(token == null) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("INAVLID ONE TIME PASSWORD").build();
		}

		return Response.ok(token).build();
	}


	//	CHANGE ROLE OF USER

	@PUT
	@Path("/change-role/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})				// 	ONLY BY SYSTEM ADMIN
	public Response changeRole(@PathParam("id") Long userId, UserRoleChangeDto userRoleChangeDto) {
		System.out.println("USER RESOURCE 1");
		UserDto userDto = userService.changeUserRole(userId,userRoleChangeDto);

		return Response.ok(userDto).build();
	}


}
