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

import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Student;
import com.csye6225.fall2018.courseservice.service.StudentsService;


@Path("students")
public class StudentResource {
StudentsService studentsService = new StudentsService();
	
	
	// ... webapi/Student/1 
	@GET
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("StudentId") int StudentId) {
		return studentsService.getStudent(StudentId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByProgram(
			@QueryParam("program") String program) {
		
		if (program == null) {
			return studentsService.getAllStudents();
		}
		return studentsService.getStudentsByProgram(program);
		
	}
	
	@DELETE
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("StudentId") int StudentId) {
		return studentsService.deleteStudent(StudentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student Student) {
		return studentsService.addStudent(Student);
	}
	
	@PUT
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("StudentId") int StudentId,   
			Student Student) {
		return studentsService.updateStudentInformation(StudentId, Student);
	}
	
}
