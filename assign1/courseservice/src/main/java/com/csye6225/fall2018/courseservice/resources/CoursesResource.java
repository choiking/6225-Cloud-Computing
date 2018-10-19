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

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.Program;
import com.csye6225.fall2018.courseservice.service.CoursesService;

//.. /webapi/myresource
@Path("courses")
public class CoursesResource {
	CoursesService courseService = new CoursesService();
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Course> getCourses() {
//		return courseService.getAllCourses();
//	}
//	
	
	//get by associated professor
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByAssociatedProfessor(
			@QueryParam("associatedProfessor") String associatedProfessor) {
		if (associatedProfessor == null) {
			return courseService.getAllCourses();
		}
		return courseService.getCoursesByAssociatedProfessor(associatedProfessor);
	}
	
	
	// ... webapi/Course/1 
	@GET
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("CourseId") int courseId) {
		return courseService.getCourse(courseId);
	}
	
	@DELETE
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("CourseId") int courseId) {
		return courseService.deleteCourse(courseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
		return courseService.addCourse(course);
	}
	
	@PUT
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("CourseId") int courseId,   
			Course course) {
		return courseService.updateCourseInformation(courseId, course);
	}
	
//	public void addCourse(String name, String department, Date joiningDate) {
//		courseService.addCourse(name, department, joiningDate);
//	}
}
