package com.loan.resource;

import java.util.List;

import com.loan.dto.CustomerRegistrationApproveDto;
import com.loan.dto.CustomerRegistrationDto;
import com.loan.service.CustomerRegistrationService;

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

@Path("/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRegistrationResource {

	CustomerRegistrationService customerRegistrationService = new CustomerRegistrationService();

	// NEW REGISTRATION OF CUSTOMER DETAILS

	@POST
	public Response registerCustomer(@Valid CustomerRegistrationDto customerRegistrationDto) {

		customerRegistrationService.saveNewCustomerRegistration(customerRegistrationDto);

		return Response.status(Response.Status.CREATED).entity(customerRegistrationDto).build();
	}

	//	VIEW REGISTRATION DETAILS BY ID

	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response getRegistrationById(@PathParam("id") Long registrationId) {

	    CustomerRegistrationDto registrationDto = customerRegistrationService.findCustomerRegistrationDtoById(registrationId);

		return Response.ok(registrationDto).build();

	}

	//	VIEW ALL REGISTRATIONS

	@GET
	@Path("/all")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response getAllRegistrations() {

		List <CustomerRegistrationDto> registrationList = customerRegistrationService.getAllCustomerRegistrationDtos();

		return Response.ok(registrationList).build();
	}

	//	UPDATE A REGISTRATION

	@PUT
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response updateRegistration(@PathParam("id") Long registrationId ,@Valid CustomerRegistrationDto customerRegistrationDto) {

		customerRegistrationDto.setCustomerRegistrationId(registrationId);

		customerRegistrationService.updateRegistration(customerRegistrationDto);

		return Response.ok(customerRegistrationDto).build();
	}

	//	DELETE A REGISTRATION

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response deleteRegistration(@PathParam("id")Long registrationId) {

		CustomerRegistrationDto customerRegistrationDto = customerRegistrationService.findCustomerRegistrationDtoById(registrationId);

		customerRegistrationService.deleteRegistration(customerRegistrationDto);

		return Response.noContent().build();
	}

	//	APPROVE REGISTRATION STATUS

	@PUT
	@Path("/approve/{id}")
	@RolesAllowed({"ADMIN","MANAGER"})
	public Response approveRegistration(@PathParam("id") Long registrationId, @Valid CustomerRegistrationApproveDto customerRegistrationApproveDto) {
		
		customerRegistrationApproveDto.setCustomerRegistrationId(registrationId);

		customerRegistrationService.approveRegistration(customerRegistrationApproveDto);

		return Response.ok(customerRegistrationApproveDto).build();
	}


	//	REJECT REGISTRATION STATUS

	@PUT
	@Path("/reject/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response rejectRegistration(@PathParam("id") Long registrationId ,@Valid CustomerRegistrationApproveDto customerRegistrationApproveDto) {

		customerRegistrationApproveDto.setCustomerRegistrationId(registrationId);

		customerRegistrationService.rejectRegistration(customerRegistrationApproveDto);

		return Response.ok(customerRegistrationApproveDto).build();
	}


	//	VALIDATE BY NIC

	@GET
	@Path("/check-nic/{nic}")
	//@RolesAllowed({"ADMIN","OFFICER"})			//	GENERAL USE THERE FORE NO ROLES ASSIGNED
	public Response validateNic(@PathParam("nic") String nic) {

		boolean isNicAvailable = customerRegistrationService.validateByNic(nic);
		return Response.ok(isNicAvailable).build();
	}

	//	VALIDATE REGISTRATION PENDING OR APPROVED

	@GET
	@Path("/check-registration/{nic}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response hasActiveRegistration(@PathParam("nic") String nic) {

		boolean isRegistrationAvailable = customerRegistrationService.hasActiveRegistration(nic);
		return Response.ok(isRegistrationAvailable).build();
	}
}