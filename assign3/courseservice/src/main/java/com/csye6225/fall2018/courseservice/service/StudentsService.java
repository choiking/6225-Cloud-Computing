package com.csye6225.fall2018.courseservice.service;


import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.courseservice.datamodel.SnsConnector;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentsService {

	
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	
	static SnsConnector snsConnector;
	

	public StudentsService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
		
		
		snsConnector = new SnsConnector();
		snsConnector.init();		
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
		Student s = getStudent(studentId);
		mapper.delete(s);		
		return s;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(String studentId, Student student) {	
		Student s = getStudent(studentId);
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
	
	
	public List<String> registerCourses(String studentId, List<String> listOfCourses) {
		Student s = getStudent(studentId);
		s.setRegisteredCourses(listOfCourses);
		CoursesService cs = new CoursesService();
		System.out.println("size: " + listOfCourses.size());
		
		for (int i = 0; i < listOfCourses.size(); i++) {
			System.out.println("The " + i + "is" + listOfCourses.get(i));
		}
		
		for (String course : listOfCourses) {
			Course c = cs.getCourse(course);
			c.getListOfRegisteredStudents().add(studentId);
			snsConnector.subscribeTopic(c.getNotificationTopic(), getStudent(studentId).getEmailId());
		}
		
		mapper.save(s);
		return listOfCourses;
	}
	
}
