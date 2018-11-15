package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;


public class CoursesService {
static HashMap<Integer, Course> course_Map = InMemoryDatabase.getCourseDB();
	
	// Getting a list of all Course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {			
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course course :course_Map.values()) {
			list.add(course);
		}
     
		return list;
	}
	
	public List<Course> getCoursesByAssociatedProfessor(String associatedProfessor) {
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course course :course_Map.values()) {
			if (course.getAssociatedProfessor().equals(associatedProfessor))
				list.add(course);
		}
     
		return list;
	}
	
	public Course addCourse(Course course) {	
		int nextAvailableId = course_Map.size() + 1;
		course.setCourseId(nextAvailableId);
		course_Map.put(nextAvailableId, course);
		return course;
	}
	
	// Getting One Course
	public Course getCourse(int courseId) {
		return course_Map.get(courseId);
	}
	
	// Deleting a Course
	public Course deleteCourse(int courseId) {
		Course deletedcourseDetails = course_Map.get(courseId);
		course_Map.remove(courseId);
		return deletedcourseDetails;
	}
	
	// Updating Course Info
	public Course updateCourseInformation(int courseId, Course course) {	
		course.setCourseId(courseId);
		course_Map.put(courseId, course) ;	
		return course;
	}
	
}
