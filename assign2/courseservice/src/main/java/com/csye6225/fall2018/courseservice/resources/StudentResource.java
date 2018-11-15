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

import com.csye6225.fall2018.courseservice.datamodel.Student;
import com.csye6225.fall2018.courseservice.service.StudentsService;


@Path("students")
public class StudentResource {
	StudentsService ss = new StudentsService();
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByDepartment(
			@QueryParam("department") String department) {
		if (department == null) {
			return ss.getAllStudents();
		}
		return ss.getStudentsByDepartment(department);
	}
	
	
	@GET
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("StudentId") String StudentId) {
		return ss.getStudent(StudentId);
	}
	

	
	@DELETE
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("StudentId") String StudentId) {
		return ss.deleteStudent(StudentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student Student) {
		return ss.addStudent(Student);
	}
	
	@PUT
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("StudentId") String StudentId,   
			Student Student) {
		return ss.updateStudentInformation(StudentId, Student);
	}
	
}