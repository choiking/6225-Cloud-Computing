package com.csye6225.fall2018.courseservice.service;


import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;



public class CoursesService {
	
	
	
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	

	public CoursesService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
	}
	
	// Getting a list of all Course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {			
		return mapper.scan(Course.class, new DynamoDBScanExpression());
	}
	
	
	public Course addCourse(Course course) {	
		mapper.save(course);
		return course;
	}
	
	// Getting One Course
	public Course getCourse(String courseId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));
		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course> ()
				.withIndexName("courseIdIndex")
				.withConsistentRead(false)
			    .withKeyConditionExpression("courseId = :v1")
			    .withExpressionAttributeValues(eav);
		List<Course> res = mapper.query(Course.class, queryExpression);
		if (res.isEmpty()) return new Course();
		return res.get(0);
		
	}
	
	// Deleting a Course
	public Course deleteCourse(String courseId) {
		
		CoursesService cs = new CoursesService();
		Course c = cs.getCourse(courseId);
		mapper.delete(c);		
		return c;
	}
	
	// Updating Course Info
	public Course updateCourseInformation(String courseId, Course course) {	
		CoursesService cs = new CoursesService();
		Course c = cs.getCourse(courseId);
		if (course.getBoardId() != null)
			c.setBoardId(course.getBoardId());
		if (course.getDepartment() != null)
			c.setDepartment(course.getDepartment());
		if (course.getProfessorId() != null)
			c.setProfessorId(course.getProfessorId());
		if (course.getTaId() != null)
			c.setTaId(course.getTaId());
		if (course.getListOfRegisteredStudents() != null)
			c.setListOfRegisteredStudents(course.getListOfRegisteredStudents());
		mapper.save(c);
		return c;
	}
	
	
	public List<Course> getCoursesByProfessorId(String professorId) {
		
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(professorId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("begins_with(professorId,:v1)")
			    .withExpressionAttributeValues(eav);	
		return mapper.scan(Course.class, scanExpression);
	}
	
}
