package com.loan.resource;

import java.util.List;
import java.util.Map;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


import com.loan.dto.LoanDocumentDto;
import com.loan.entity.LoanDocument;
import com.loan.service.LoanDocumentService;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;


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
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

@Path("/loanDocuments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoanDocumentResource {
	
	
	LoanDocumentService loanDocumentService = new LoanDocumentService();

	@POST
	public Response saveLoanDocument(@Valid LoanDocumentDto loanDOcumentDto) {
		System.out.println("TEST");
		
		//loanDocumentService.uploadDocument();
		
		return Response.status(Response.Status.CREATED).entity(loanDOcumentDto).build();
		
	}
	
	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response findLoanDocument(@PathParam("id") Long loanDocumentid) {

		LoanDocumentDto loanDocumentdto = loanDocumentService.searchLoanDocumentDetails(loanDocumentid);

	    return Response.ok(loanDocumentdto).build();
	}
	
	@GET
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response getAllLoanDocuments() {
		
		List<LoanDocumentDto> loanDocuments = loanDocumentService.getAllLoanDocuments();
		
		return Response.ok(loanDocuments).build();
	}
	
	@PUT
	@Path("/{id}")
	@RolesAllowed({"ADMIN","OFFICER"})
	public Response updateLoanDocument(@PathParam("id") Long loanDocumentId,LoanDocumentDto loanDocumentDto) {

		loanDocumentDto.setDocumentId(loanDocumentId);
		
		loanDocumentService.updateLoanDocument(loanDocumentDto);
		
		return Response.ok(loanDocumentDto).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteLoanDocument(@PathParam("id") Long loanDocumentDtoid) {
		
		loanDocumentService.deleteLoanDocument(loanDocumentDtoid);
		
		return Response.noContent().build();
	}
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RolesAllowed({"ADMIN","OFFICER","CUSTOMER"})
	public Response uploadFile(MultipartFormDataInput input){
		
		System.out.print("UPLOAD TEST");
		
		
		try {
			
			Map <String,List<InputPart>>formData = input.getFormDataMap();
			
			//	GET APPLICATION ID 
			String applicationIdText = formData.get("applicationId").get(0).getBody(String.class,null);
			
				Long applicationId = Long.parseLong(applicationIdText);
			
			//	GET DOCUMENT TYP
			String documentTypeText = formData.get("documentType").get(0).getBody(String.class,null);
			
			//	GET ACTUAL FILE
			InputPart filePart = formData.get("file").get(0);
			
			InputStream fileInputStream = filePart.getBody(InputStream.class,null);
			
			//	GET ORIGINAL FILE NAME
			
			MultivaluedMap<String ,String> headers = filePart.getHeaders();
			
			String contentDisposition = headers.getFirst("Content-Disposition");
			
			String fileName = contentDisposition.replaceFirst(".*filename=\"?([^\"]+)\"?.*", "$1");
			
			// Convert InputStream → byte[]
			
			ByteArrayOutputStream  outputStream = new ByteArrayOutputStream ();
			
			byte[] buffer = new byte[1024];
			
			int bytesRead;
			
			while((bytesRead = fileInputStream.read(buffer)) != -1) {
				
				outputStream.write(buffer,0,bytesRead);
			}
			
			byte[] fileData = outputStream.toByteArray();
			
			// SEND EVERYTING TO SERVICE
			
			loanDocumentService.uploadDocument(applicationId, documentTypeText, fileName, fileData);
			
			return Response.status(Response.Status.CREATED).entity("DOCUMENT UPLOADED SUCCESSFULLY.").build();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("FILE UPLOAD FAILED.")
					.build();
		}
	}
	
	
	@GET
	@Path("/download/{documentId}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadDocument(@PathParam("documentId") Long documentId) {
		
		
		LoanDocument loanDocument = loanDocumentService.getLoanDocument(documentId);
		
		byte[] fileData = loanDocument.getFileData();
		
		
		return Response.ok(fileData)
				.header("Content-Disposition","attachment; filename=\""+loanDocument.getFileName()+"\"")
				.build();	}
	
	
	
}
