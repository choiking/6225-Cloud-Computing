package com.csye6225.fall2018.courseservice.service;


import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentsService {

	
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	

	public StudentsService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
	}
	
	// Getting a list of all Student
	// GET "..webapi/students"
	public List<Student> getAllStudents() {	
		
		return mapper.scan(Student.class, new DynamoDBScanExpression());
	}
	
	
	public Student addStudent(Student student) {	
		mapper.save(student);
		return student;
	}
	
	// Getting One Student
	public Student getStudent(String studentId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(studentId));
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student> ()
				.withIndexName("studentIdIndex")
				.withConsistentRead(false)
			    .withKeyConditionExpression("studentId = :v1")
			    .withExpressionAttributeValues(eav);
		List<Student> res = mapper.query(Student.class, queryExpression);
		if (res.isEmpty()) return new Student();
		return res.get(0);
		
	}
	
	// Deleting a Student
	public Student deleteStudent(String studentId) {
		
		StudentsService ss = new StudentsService();
		Student s = ss.getStudent(studentId);
		mapper.delete(s);		
		return s;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(String studentId, Student student) {	
		StudentsService ss = new StudentsService();
		Student s = ss.getStudent(studentId);
		if (student.getDepartment() != null)
			s.setDepartment(student.getDepartment());
		if (student.getRegisteredCourses() != null)
			s.setRegisteredCourses(student.getRegisteredCourses());
		if (student.getFirstName() != null)
			s.setFirstName(student.getFirstName());
		if (student.getLastName() != null)
			s.setLastName(student.getLastName());
		if (student.getJoiningDate() != null)
			s.setJoiningDate(student.getJoiningDate());
		mapper.save(s);
		return s;
	}
	
	
	public List<Student> getStudentsByDepartment(String department) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(department));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("begins_with(department,:v1)")
			    .withExpressionAttributeValues(eav);	
		return mapper.scan(Student.class, scanExpression);
	}
	
}
