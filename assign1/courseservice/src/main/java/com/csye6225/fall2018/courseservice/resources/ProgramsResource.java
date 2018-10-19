package com.csye6225.fall2018.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Program;
import com.csye6225.fall2018.courseservice.service.ProgramsService;

@Path("programs")
public class ProgramsResource {
	ProgramsService programService = new ProgramsService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getProgramsByBeginTerm(
			@QueryParam("beginTerm") String beginTerm) {	
		if (beginTerm == null) {
			return programService.getAllPrograms();
		}
		return programService.getProgramByBeginTerm(beginTerm);	
	}
	
	// ... webapi/Program/1 
	@GET
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("ProgramId") int programId) {
		return programService.getProgram(programId);
	}
	
	@DELETE
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("ProgramId") int programId) {
		return programService.deleteProgram(programId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
		return programService.addProgram(program);
	}
	
	@PUT
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("ProgramId") int programId, 
			Program program) {
		return programService.updateProgramInformation(programId, program);
	}
}
